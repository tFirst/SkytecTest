package com.skytec.repository;

import com.skytec.bean.Duels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface InfoRepository extends CrudRepository<Duels, Long> {
    Collection<Duels> findAll();
}
