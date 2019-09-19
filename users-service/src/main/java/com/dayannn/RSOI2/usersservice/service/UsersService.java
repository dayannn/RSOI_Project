package com.dayannn.RSOI2.usersservice.service;

import com.dayannn.RSOI2.usersservice.entity.*;
import com.dayannn.RSOI2.usersservice.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    Playlist createPlaylist(Playlist playlist, String username);
    Playlist getPlaylist(Long id);
    Playlist findPlaylistById(Long id);
    void deletePlaylist(Long id);
    Song addSong(Song song);
    Song findSongById(Long id);
    ResponseEntity addSong(Long id, Long song_id);
    ResponseEntity deleteSong(Long id, Long song_id);
    ResponseEntity getAllSongs();
    void deleteSong(Long id);
    Artist createArtst(Artist artist);
    Artist findArtistById(Long id);
    void deleteArtist(Long id);
    Album createAlbum(Album album);
    Album findAlbumById(Long id);
    void deleteAlbum(Long id);
    List<Playlist> getPlaylists(String username);
    List<Song> search(String name);
    List<Artist> searchArtists(String name);
    Artist getArtist(Long id);
    Album getAlbum(Long id);
    List<Artist> getArtists();

    ResponseEntity healthCheck();
}

