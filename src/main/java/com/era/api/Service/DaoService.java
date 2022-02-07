package com.era.api.Service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaoService {
	
	
	 private static final Logger logger = LoggerFactory.getLogger(DaoService.class);	    
	    
	    private final static String Login_AgentBanking = "IBANKING.DPR_USER_SIGNIN_EPAY";
	    private final static String Login_CBS = "mybank.dpk_security_lv.DPR_USER_SIGNIN_EPAY";
	    
	    
		
	    @Autowired
	  	 private EntityManager entityManager;

		public void LoginSubmit(String userId, String password,String sInternalCardID,
	  			 					  String sPhysicalAddressServer,String sPhysicalAddressClient,
	  			 					  String sRemoteIPAddress,String UserType) {
	  		 
		    	Map<String, Object> outputMap = new HashMap<>();
		    	StoredProcedureQuery query = null;
		    	//LoginForm oLoginForm = new LoginForm();
		    	System.out.print("this.entityManager ==>>> "+this.entityManager);
		   try { 
		        if(UserType.equals("AgentBanking")) {
		            query = this.entityManager.createStoredProcedureQuery(Login_AgentBanking);				
				}else if(UserType.equals("Conventional")) {	      
					  query = this.entityManager.createStoredProcedureQuery(Login_CBS);			
				}

		        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
		        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
		        query.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
		        query.registerStoredProcedureParameter(7, String.class, ParameterMode.OUT);
		        query.registerStoredProcedureParameter(8, String.class, ParameterMode.OUT);
		        query.registerStoredProcedureParameter(9, int.class, ParameterMode.OUT);
		        query.registerStoredProcedureParameter(10, String.class, ParameterMode.OUT);

		        query.setParameter(1, userId);
		        query.setParameter(2, password);
		        query.setParameter(3, sInternalCardID);
		        query.setParameter(4, sPhysicalAddressServer);
		        query.setParameter(5, sPhysicalAddressClient);
		        query.setParameter(6, sRemoteIPAddress);
//		        oLoginForm.setUserID((String) query.getOutputParameterValue(7));
//		        oLoginForm.setSession((String) query.getOutputParameterValue(8));
//		        oLoginForm.setErrorCode(""+query.getOutputParameterValue(9));
//		        oLoginForm.setErrorMessage((String) query.getOutputParameterValue(10));

		        }catch(Exception ex) {
		        	
		        	ex.printStackTrace();
		        }
		        
		        //return oLoginForm;
		    }
}
