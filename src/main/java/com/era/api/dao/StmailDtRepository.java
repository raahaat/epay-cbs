package com.era.api.dao;

import com.era.api.model.StmailDt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StmailDtRepository extends JpaRepository<StmailDt, String> {

    @Query(value = "SELECT mailid,DECODE (fndflg, 'N', NULL, 'FTR') fndflg,DECODE (utlflg, 'N', NULL, 'PST') pstflg, DECODE (utlflg, 'N', NULL, 'ADV') advflg,DECODE (utlflg, 'N', NULL, 'BIL') bilflg,DECODE (utlflg, 'N', NULL, 'SDP') sdpflg,DECODE (utlflg, 'N', NULL, 'OTH') othflg,DECODE (utlflg, 'N', NULL, 'PRE') preflg FROM stmaildt WHERE mailid =?1 AND actflg != 'N'", nativeQuery = true)
    List<String> getStmailDt(String mailId);
}
