package com.era.api.dao;

import com.era.api.model.STMAILID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
@Repository
public interface StmailidRepository extends JpaRepository<STMAILID,String> {
    @Query(value = "SELECT LOGINTOTAL,LOGINTODAY,MAILID,PASSWORD,CUSCOD,LSTLOGIN,ACTFLG,USERID,TODAY,ADOPTFLG,LASTPASSCHANGED,CONFFLG,SESSIONS,LOGINEXPIRED,PASWDCNT FROM MYBANK.STMAILID WHERE STMAILID.USERID = ?1 AND STMAILID.PASSWORD = ?2",nativeQuery = true)
    STMAILID loginUser(String userId,String password);
    @Query(value="select dfn_ip_numeric (?1) from dual", nativeQuery=true)
    String getNumericIp(String ip);
    @Query(value="SELECT  actflg FROM MYBANK.lk_ip_range WHERE :numericIp BETWEEN dfn_ip_numeric (from_ip) AND dfn_ip_numeric (to_ip)  AND rownum =1", nativeQuery=true)
    String getActFlag(@RequestParam("numericIp") String numericIp);
    @Query(value="  SELECT plabon.dfn_mybank_password_encryption (?1,?2) from dual", nativeQuery=true)
    String passwordEncoding(String userId,String password);
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE plabon.STMAILID SET lstlogin = SYSDATE, paswdcnt  = 0 WHERE MAILID=:mailId", nativeQuery = true)
    void updateLastLoginAndPaswdcnt(@Param("mailId") String mailId);
    @Query(value = "SELECT COUNT(*) FROM PLABON.STMAILID where mailid=:mailId", nativeQuery = true)
    Integer findRowNumber(@Param("mailId") String mailId);
}
