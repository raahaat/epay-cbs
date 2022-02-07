package com.era.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LkOperationCode {
    @Id
    @Column(name = "procod")
    private String procod;
    @Column(name = "pronam")
    private String pronam;
    @Column(name = "actnum")
    private String actnum;

}
