package com.dayannn.RSOI2.usersservice.repository;

import com.dayannn.RSOI2.usersservice.entity.Rightholder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RightholderRepository extends JpaRepository<Rightholder, Long> {
    Rightholder findByLogin(String login);
}
