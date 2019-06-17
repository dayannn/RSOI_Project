package com.dayannn.RSOI2.usersservice.service;

import com.dayannn.RSOI2.usersservice.entity.*;
import com.dayannn.RSOI2.usersservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {
    User findUserById(Long id) throws UserNotFoundException;
    User findUserByLogin(String login) throws UserNotFoundException;
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUser(Long id);
    Rightholder createRightholder(Rightholder rightholder);
    Rightholder findRightholderById(Long id);
    List<Rightholder> getAllRightholders();
    void deleteRightholder(Long id);
    Playlist createPlaylist(Playlist playlist);
    Playlist findPlaylistById(Long id);
    void deletePlaylist(Long id);
    Song addSong(Song song);
    Song findSongById(Long id);
    void deleteSong(Long id);
    Artist createArtst(Artist artist);
    Artist findArtistById(Long id);
    void deleteArtist(Long id);
    Album createAlbum(Album album);
    Album findAlbumById(Long id);
    void deleteAlbum(Long id);

    ResponseEntity healthCheck();
}

