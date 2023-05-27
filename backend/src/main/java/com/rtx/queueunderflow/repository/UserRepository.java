package com.rtx.queueunderflow.repository;

import com.rtx.queueunderflow.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByeMail(String email);
}
