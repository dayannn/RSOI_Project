package com.dayannn.RSOI2.usersservice.repository;

import com.dayannn.RSOI2.usersservice.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
