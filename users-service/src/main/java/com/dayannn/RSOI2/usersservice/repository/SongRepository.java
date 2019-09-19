package com.dayannn.RSOI2.usersservice.repository;

import com.dayannn.RSOI2.usersservice.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByNameIgnoreCaseContaining(String name);
}
