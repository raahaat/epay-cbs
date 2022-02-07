package com.era.api.Service;

import com.era.api.Exception.LoginException;
import com.era.api.Exception.ResourceNotFoundException;
import com.era.api.dao.LogTableRepository;
import com.era.api.dao.StmailidRepository;
import com.era.api.model.STMAILID;
import com.era.api.model.SessionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    StmailidRepository stmailidRepository;
    @Autowired
    LogTableRepository logTableRepository;

    public void saveLogValues(SessionLog sessionLog){
        System.out.println("ses log "+sessionLog);
        logTableRepository.save(sessionLog);
    }

    public STMAILID loginUSer(String userName, String password){
        String bankingType;
        String encryptedPassword = stmailidRepository.passwordEncoding(userName,password);
       // System.out.println("encrypted pass"+encryptedPassword);
        STMAILID stmailid= stmailidRepository.loginUser(userName,encryptedPassword);
        System.out.println("service =" +stmailid);
        bankingType =getEmailValidate(stmailid.getUSERID());
        System.out.println("banking type "+bankingType);
        if(stmailid == null) throw new ResourceNotFoundException("No record found");
        else{
            if(stmailid.getACTFLG().equals("N")){
                throw new LoginException("Act flag ","User ID not Active.<br>Sign In Denied");
            }else if(stmailid.getACTFLG().equals("P"))
                throw new LoginException("Act flag ", "Unauthorized access from Internet.<br>Sign In Denied.");
             return stmailid;
        }

    }
    public static String getEmailValidate(String userId)
    {
        String rValue = "";
        try
        {
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = Boolean.valueOf(userId.matches(emailregex));
            if (!b.booleanValue()) {
                rValue = "AgentBanking";
            } else if (b.booleanValue() == true) {

                rValue = "Conventional";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return rValue;
    }


}
