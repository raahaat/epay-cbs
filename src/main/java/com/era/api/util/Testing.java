package com.era.api.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class Testing {
    public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException {

        String encryptKey = "ePayment#123ERA&123BankAsia@123!";
        String encryptVal= "aElYaeYHyWhxvsMmvqfeOJsCjochQtn4Pk1/8rlmni4CEGoha7Vo+MAlCqDnKzyFYDvySGgA1W/NGJl0r8zrsWo4kpBSAKzYnm4SkDcXf6YwUlCwiHa4Dpi4rV2wTx/iCBmz6VG8sEjUjdwmN4eLkQ==";
        String decryptKey;


//        String re = EncryptDecrypt.encrypt(encryptKey, encryptVal);
//        System.out.println(re);

        System.out.println(EncryptDecrypt.decryptWithIV(encryptVal, encryptKey));

    }
}
