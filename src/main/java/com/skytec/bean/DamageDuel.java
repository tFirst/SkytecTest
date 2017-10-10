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
@Table(name = "damage_duel")
public class DamageDuel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "duel_id")
    private Long duelId;

    @Column(name = "damager_id")
    private Long damagerId;

    @Column(name = "damage")
    private Long damage;
}
