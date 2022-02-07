package com.era.api.Service;

import com.era.api.dao.StmailDtRepository;
import com.era.api.dto.StmailDtDto;
import com.era.api.model.StmailDt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StmailDtService {

    @Autowired
    StmailDtRepository stmailDtRepository;

    public List<StmailDtDto> getStmailDt(String mailId)
    {
       List<String> values = stmailDtRepository.getStmailDt(mailId);

       List<StmailDtDto> res = new ArrayList<>();
       for (int i =0; i<values.size(); i++)
       {
           List<String> split = Arrays.asList(values.get(i).split(","));
           StmailDtDto dto = new StmailDtDto();
           dto.setMailid(split.get(0));
           dto.setFndflg(split.get(1));
           dto.setPstflg(split.get(2));
           dto.setAdvflg(split.get(3));
           dto.setBilflg(split.get(4));
           dto.setSdpflg(split.get(5));
           dto.setOthflg(split.get(6));
           dto.setPreflg(split.get(7));
           res.add(dto);
       }

       return res;
    }

}
