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
@Table(name = "current_duels_values")
public class CurrentDuelsValues {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "duel_id")
    private Long duelId;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private Long health;
}
