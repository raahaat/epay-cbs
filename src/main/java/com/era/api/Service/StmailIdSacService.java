package com.era.api.Service;

import com.era.api.dao.StmailIdSacRepository;
import com.era.api.dto.StmailIdSacDto;
import com.era.api.model.StmailIdSac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StmailIdSacService {

    @Autowired
    StmailIdSacRepository stmailIdSacRepository;

    public List<StmailIdSacDto> getData(String mailId)
    {
        List<StmailIdSacDto> res = new ArrayList<>();
        List<String> values = stmailIdSacRepository.getData(mailId);

        for(int i = 0; i<values.size(); i++) {
            List<String> split = Arrays.asList(values.get(i).split(","));
            StmailIdSacDto dto = new StmailIdSacDto();
            dto.setSrcAcc(split.get(0));
            dto.setComcod(split.get(1));
            System.out.println(dto);
            res.add(dto);
        }

        System.out.println(res);
        return res;

    }
}
