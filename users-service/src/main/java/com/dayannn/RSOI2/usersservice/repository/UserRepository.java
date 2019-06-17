package com.dayannn.RSOI2.usersservice.repository;

import com.dayannn.RSOI2.usersservice.entity.Rightholder;
import com.dayannn.RSOI2.usersservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
