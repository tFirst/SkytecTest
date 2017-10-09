package com.skytec.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import javax.persistence.*;


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

    @Column
    private Long opponentOne;

    @Column
    private Long opponentTwo;

    @Column(name = "winner_id")
    private Long winner;
}
