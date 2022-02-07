package com.era.api.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "STMAILID")
public class STMAILID {
    @Id
    @Column(name = "MAILID",nullable = false)
    private String MAILID;
    @Column(name = "PASSWORD")
    private String PASSWORD;
    @Column(name = "CUSCOD")
    private String CUSCOD;
    @Column(name = "LSTLOGIN")
    private Date LSTLOGIN;
    @Column(name = "ACTFLG")
    private String ACTFLG;
    @Column(name = "USERID")
    private String USERID;
      @Column(name = "TODAY")
    private Date TODAY;
    @Column(name = "ADOPTFLG")
    private String ADOPTFLG;
    @Column(name = "LASTPASSCHANGED")
     private Date LASTPASSCHANGED;
    @Column(name = "CONFFLG")
    private String CONFFLG;
    @Column(name = "SESSIONS")
    private Integer SESSIONS;
    @Column(name = "LOGINEXPIRED")
    private Integer LOGINEXPIRED;
    @Column(name = "PASWDCNT")
    private Integer PASWDCNT;
    @Column(name = "LOGINTODAY")
    private Integer LOGINTODAY;
    @Column(name = "LOGINTOTAL")
    private Integer LOGINTOTAL;

}
