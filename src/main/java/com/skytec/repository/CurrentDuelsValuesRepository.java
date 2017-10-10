package com.skytec.repository;


import com.skytec.bean.CurrentDuelsValues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CurrentDuelsValuesRepository
        extends CrudRepository<CurrentDuelsValues, Long> {
    Collection<CurrentDuelsValues> findAll();

    CurrentDuelsValues findByUserId(Long userId);

    Collection<CurrentDuelsValues> findByDuelId(Long duelId);
}
