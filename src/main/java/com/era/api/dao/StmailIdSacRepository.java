package com.era.api.dao;

import com.era.api.dto.StmailIdSacDto;
import com.era.api.model.StmailIdSac;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StmailIdSacRepository extends JpaRepository<StmailIdSac, String> {

    @Query(value = "SELECT srcacc, dfn_bank_code(actflg) FROM stmailid_sac WHERE mailid =?1 AND actflg != 'N' ", nativeQuery = true)
    List<String> getData( String mailId);
}
