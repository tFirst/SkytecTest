package com.skytec.repository;

import com.skytec.bean.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SessionsRepository extends CrudRepository<Session, Long> {

    Collection<Session> findAll();

    Session findBySessionId(Long sessionId);

    Session findByUserId(Long userId);
}
