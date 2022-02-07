package com.era.api.Controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.era.api.Exception.NoDataException;
import com.era.api.Service.*;
import com.era.api.dao.StmailIdSacRepository;
import com.era.api.dao.StmailidRepository;
import com.era.api.dto.ActInfoDto;
import com.era.api.dto.StmailDtDto;
import com.era.api.dto.StmailIdSacDto;
import com.era.api.model.AccountInfo;
import com.era.api.model.STMAILID;
import com.era.api.model.SessionLog;
import com.era.api.model.StmailDt;
import com.era.api.util.EncryptDecrypt;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin("http://10.11.201.169:4200")
@CrossOrigin
@RequestMapping("/")
public class LoginAction {

	final String secretKey =  "ePayment#123ERA&123BankAsia@123!";

	@Autowired
	DaoService oDaoService;
	@Autowired
	LoginService loginService;
	@Autowired
	StmailidRepository stmailidRepository;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpSession httpSession;
	@Autowired
	AccountInfoService accountInfoService;
	@Autowired
	StmailIdSacRepository stmailIdSacRepository;
	@Autowired
	StmailIdSacService stmailIdSacService;
	@Autowired
	StmailDtService stmailDtService;


	@GetMapping("v1/api/actinfo")
	public ActInfoDto getActInfo(@RequestParam("actNum") String actNum)
	{
		return accountInfoService.getActInfo(actNum);
	}

	@GetMapping("v1/api/hello")
	public String hello()
	{
		return "Hello world";
	}
	
	@GetMapping("v1/api/test")
	public AccountInfo getAccInfo()
	{
		return accountInfoService.getData("001", "004", "04ADV090754", "00059728", "ACT");

	}
	@GetMapping("v1/api/sourceaccnum")
	public List<StmailIdSacDto> getStmailIdSac(@RequestParam("mailId") String mailId)
	{

		return stmailIdSacService.getData(mailId);

	}
	@GetMapping("v1/api/test2")
	public List<StmailDtDto> getStmailDt()
	{

		return stmailDtService.getStmailDt("khaleq@bankasia.com.bd");

	}


//
//	@GetMapping("v1/api/login1/{text}")
//	public String test(@PathVariable("text") String text, @RequestParam("userName") String userName, @RequestParam("password") String password) {
//		return "v1/api/login1: "+text+" with userName: "+userName+" with password: "+password;
//	}


	//The session data can be tested through this API
	@GetMapping("v1/api/session")
	public ResponseEntity<SessionLog> getSessionData(){
	//ask if validation is needed
		SessionLog sessionLog = new SessionLog();
		sessionLog.setAmount(httpSession.getAttribute("amount").toString());
		sessionLog.setOrderNo(httpSession.getAttribute("orderNo").toString());
		sessionLog.setGroupID(httpSession.getAttribute("groupID").toString());
		sessionLog.setMarchant(httpSession.getAttribute("marchant").toString());
		sessionLog.setRedirectUrl(httpSession.getAttribute("redirectUrl").toString());
		return new ResponseEntity<>(sessionLog,HttpStatus.OK);
	}



	@GetMapping("v1/api/login")
	public ResponseEntity<Map<String,Object>> indexMapping( @RequestParam("epayId") String epayId,@RequestParam(value = "userName", required=true) String userName
			, @RequestParam(value = "password", required=true) String password) throws ParseException, SQLException, ClassNotFoundException, GeneralSecurityException, UnsupportedEncodingException {
		Map<String,Object>response = new HashMap<>();
		if(userName ==null || password ==null){
			response.put("message","Invalid user id or password");
			response.put("errorCode","1");
		}
		String addingPlus = epayId.replace(' ', '+');
		String decryptedValue = EncryptDecrypt.decryptWithIV(addingPlus, secretKey);
		System.out.println("decrypted data =====" + decryptedValue);

		String ipAddress = request.getRemoteAddr();
		System.out.println("ipAddress ="+ipAddress);

		//getting the session data and setting the values
		try {
			JSONObject decryptedObj = new JSONObject(decryptedValue);
			System.out.println(decryptedObj);
			SessionLog sessionVal = new SessionLog();
			sessionVal.setAmount(decryptedObj.getString("amount"));
			sessionVal.setOrderNo(decryptedObj.getString("orderNo"));
			sessionVal.setRedirectUrl( decryptedObj.getString("redirectUrl"));
			sessionVal.setGroupID(decryptedObj.getString("groupID"));
			sessionVal.setMarchant(decryptedObj.getString("marchant"));
			response.put("sessiondata",sessionVal);
			httpSession.setAttribute("amount", decryptedObj.getString("amount"));
			httpSession.setAttribute("orderNo",  decryptedObj.getString("orderNo"));
			httpSession.setAttribute("redirectUrl", decryptedObj.getString("redirectUrl"));
			httpSession.setAttribute("groupID", decryptedObj.getString("groupID"));
			httpSession.setAttribute("marchant", decryptedObj.getString("marchant"));
			SessionLog sessionLog = new SessionLog();
			sessionLog.setRequestIP(ipAddress);
			sessionLog.setAmount(httpSession.getAttribute("amount").toString());
			sessionLog.setOrderNo(httpSession.getAttribute("orderNo").toString());
			sessionLog.setGroupID(httpSession.getAttribute("groupID").toString());
			sessionLog.setMarchant(httpSession.getAttribute("marchant").toString());
			sessionLog.setRedirectUrl(httpSession.getAttribute("redirectUrl").toString());
			sessionLog.setRequestDate(new Date());


			System.out.println();
			loginService.saveLogValues(sessionLog);

		}catch (JSONException err){
			System.out.println();
			response.put("message","failed to set data in session");
			response.put("errorCode","1");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		STMAILID stmailid = loginService.loginUSer(userName,password);
		if(stmailid ==null) {
			response.put("message","invalid username or password");
			response.put("errorCode","1");
		}
		System.out.println("controller ="+stmailid);

		System.out.println("get act flag == "+stmailidRepository.getActFlag(stmailidRepository.getNumericIp(ipAddress)));

		//check if within proper IP
//		if(stmailidRepository.getActFlag(stmailidRepository.getNumericIp(ipAddress)).equals("P")) {
//			response.put("message","Unauthorized access from Internet.<br>Sign In Denied.");
//			response.put("errorCode","1");
//			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		//check flags
		if(stmailid.getACTFLG() != "N" || !stmailid.getACTFLG().equals("N")){
			if(stmailid.getLOGINTODAY().equals(null)){
				stmailid.setLOGINTODAY(1);
			}
			if(stmailid.getLOGINTOTAL().equals(null)){
				stmailid.setLOGINTOTAL(1);
			}

		}
		else if(stmailidRepository.findRowNumber(stmailid.getMAILID()) > 1)
		{
			response.put("message","More than one User ID. Sign In Denied.");
			response.put("errorCode","1");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else{
			throw new NoDataException("1", "Invalid User ID. Sign In Denied");
		}
		//updating counter and last login date
		stmailidRepository.updateLastLoginAndPaswdcnt(stmailid.getMAILID());
		response.put("message",stmailid);
		response.put("errorCode","0");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}




}
