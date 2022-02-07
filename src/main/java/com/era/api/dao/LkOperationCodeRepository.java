package com.era.api.dao;

import com.era.api.model.LkOperationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LkOperationCodeRepository extends JpaRepository<LkOperationCode, String> {
    @Query(value = "SELECT procod, pronam, actnum FROM lk_operation_code WHERE actflg = 'Y' AND (  actnum IS NOT NULL) AND TRUNC (sysdate) BETWEEN TRUNC (strdat) AND TRUNC (enddat) ORDER BY pronam",nativeQuery = true)
    public List<String> getOperationCode();
}
