package com.skytec.repository;

import com.skytec.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Collection<User> findAll();

    User findByLogin(String login);

    User findById(Long id);
}
