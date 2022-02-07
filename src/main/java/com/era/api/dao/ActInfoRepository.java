package com.era.api.dao;

import com.era.api.dto.ActInfoDto;
import com.era.api.model.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActInfoRepository extends JpaRepository<AccountInfo, String> {
    @Query(value = "SELECT actype,comcod,actnum,actdis,schema_name FROM PLABON.ACCOUNT_INFO WHERE comcod = ?1 AND brancd = ?2 AND actnum = ?3 AND cuscod = ?4 AND ?5 IN ('ACT', 'ILT')", nativeQuery = true)
    AccountInfo getData(String comcod, String brancd, String actnum, String cuscod, String acstat);

    @Query(value = "select ACTTIT, CURBAL from PLABON.ACCOUNT_INFO where ACTNUM = ?1", nativeQuery = true)
    String getActInfo(String actNum);
}
