package com.era.api.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOUNT_INFO")
@ToString
public class AccountInfo {

    @Id
    @Column(name = "actnum", nullable = false)
    private String actNum;
//    @Column(name = "barncd", nullable = false)
//    private String barncd;
    @Column(name = "comcod", nullable = false)
    private String comcod;
//    @Column(name = "cuscod", nullable = false)
//    private String cuscod;
//    @Column(name = "acstat", nullable = false)
//    private String acstat;
    @Column(name = "actdis", nullable = false)
    private String actdis;
    @Column(name = "schema_name", nullable = false)
    private String schema_name;
    @Column(name = "actype", nullable = false)
    private String actype;


}
