package com.skytec.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "Duels")
public class Duels {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "user_one_id")
    private Long userOneId;

    @Column(name = "user_two_id")
    private Long userTwoId;

    @Column(name = "winner_id")
    private Long winnerId;
}
