package com.skytec.repository;

import com.skytec.bean.Duels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface DuelsRepository extends CrudRepository<Duels, Long> {
    Collection<Duels> findAll();

    Duels findByUserOneId(Long userOneId);

    Duels findByUserTwoId(Long userTwoId);

    Duels findById(Long id);

    Duels findByDate(Date date);
}
