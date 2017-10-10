package com.skytec.repository;

import com.skytec.bean.ReadyToDuel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public interface ReadyToDuelRepository extends CrudRepository<ReadyToDuel, Long> {

    Collection<ReadyToDuel> findAll();

    ReadyToDuel findByUserId(Long id);
}
