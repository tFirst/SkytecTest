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
@Table(name = "ready_to_duel")
public class ReadyToDuel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private Long userId;
}
