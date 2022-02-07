package com.era.api.Service;

import com.era.api.dao.ActInfoRepository;
import com.era.api.dto.ActInfoDto;
import com.era.api.model.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountInfoService {

    @Autowired
    ActInfoRepository actInfoRepository;

    public AccountInfo getData(String comcod, String brancd, String actnum, String cuscod, String acstat)
    {

        return actInfoRepository.getData(comcod, brancd, actnum, cuscod, acstat );
    }

    public ActInfoDto getActInfo(String actNum)
    {
        String response = actInfoRepository.getActInfo(actNum);
        ActInfoDto actInfoDto = new ActInfoDto();
        actInfoDto.setActTit(response.split(",")[0]);
        actInfoDto.setCurBal(Double.valueOf(response.split(",")[1]));
        actInfoDto.setCurrency("BDT");
        //actInfoDto.setCurBal(Long.valueOf(obbj.get(1)));
        return  actInfoDto;
    }
}
