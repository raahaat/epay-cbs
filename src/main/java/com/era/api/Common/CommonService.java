package com.era.api.Common;

import org.springframework.stereotype.Service;

@Service
public class CommonService {

	public static String getUserValidate(String userId) {
        String rValue="";

        try {
            //String mydomain = "enamjul@a.net";
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = userId.matches(emailregex);

            if (b == false) {
               // System.out.println("Email Address is Invalid");
            	 rValue = "AgentBanking";
              
            } else if (b == true) {
            	  rValue = "Conventional";
                //System.out.println("Email Address is Valid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return rValue;

    }
}
