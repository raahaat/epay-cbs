package com.era.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "stmailid_sac")
public class StmailIdSac {

    @Id
    @Column(name = "mailid", nullable = false)
    private String mailId;
    @Column(name = "srcacc", nullable = false)
    private String srcAcc;
    @Column(name = "actflg", nullable = false)
    private String actFlg;
//    @Column(name = "oprstamp", nullable = false)
//    private String oprStamp;
//    @Column(name = "timestamp")
//    private Date timeStamp;
}
