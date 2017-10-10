package com.skytec.repository;

import com.skytec.bean.DamageDuel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DamageDuelRepository extends CrudRepository<DamageDuel, Long> {
    Collection<DamageDuel> findAll();

    DamageDuel findByDuelId(Long duelId);
}
