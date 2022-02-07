package com.era.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STMAILDT")
public class StmailDt {

    @Id
    @Column(name = "mailid")
    private String mailId;
    @Column(name = "fndflg")
    private String fndFlg;
    @Column(name = "utlflg")
    private String utlFlg;

}
