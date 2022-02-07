package com.era.api.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActInfoDto {

    @Column(name = "ACTTIT")
    private String actTit;
    @Column(name = "CURBAL")
    private Double curBal;
    private String currency;
}
