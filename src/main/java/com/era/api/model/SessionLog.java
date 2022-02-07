package com.era.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "LOG_TABLE")
public class SessionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String RequestIP;
    private String amount;
    private String orderNo;
    private String redirectUrl;
    private String groupID;
    private String marchant;
    private Date RequestDate;
}
