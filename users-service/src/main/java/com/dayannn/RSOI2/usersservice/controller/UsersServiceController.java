package com.dayannn.RSOI2.usersservice.controller;

import com.dayannn.RSOI2.usersservice.entity.*;
import com.dayannn.RSOI2.usersservice.exception.UserNotFoundException;
import com.dayannn.RSOI2.usersservice.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class UsersServiceController {
    private UsersService usersService;
    private Logger logger = LoggerFactory.getLogger(UsersServiceController.class);

    @Autowired
    UsersServiceController(UsersService usersService){
        this.usersService = usersService;
//
//        Rightholder rightholder = new Rightholder();
//        rightholder.setName("Atlantic records");
//        rightholder.setLogin("rh1");
//        usersService.createRightholder(rightholder);
//
//        Artist artist = new Artist();
//        artist.setName("Etta James");
//        artist.setInfo("She's cool");
//        artist.setRightholder(rightholder);
//        usersService.createArtst(artist);
//
//        Album album = new Album();
//        album.setArtist(artist);
//        album.setName("Best songs");
//        album.setInfo("Greatest songs by Etta James");
//        usersService.createAlbum(album);
//
//        Song song = new Song();
//        song.setAlbum(album);
//        song.setFilePath("/fsef");
//        song.setName("I'd rather be blind");
//        usersService.addSong(song);
//
//        Song song2 = new Song();
//        song2.setAlbum(album);
//        song2.setFilePath("/fseffsd");
//        song2.setName("At last");
//        usersService.addSong(song2);

    }

    @PostMapping(value = "/users")
    public User createUser(@RequestBody User user){
        logger.info("[POST] /users", user);
        return usersService.createUser(user);
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){
        logger.info("[GET] /users");
        return usersService.getAllUsers();
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        logger.info("[GET] /users/" + id);
        return usersService.findUserById(id);
    }

    @GetMapping(value = "/users/find")
    public User getUserByLogin(@RequestParam(value = "login") String login) throws UserNotFoundException {
        logger.info("[GET] /users/find ", login);
        return usersService.findUserByLogin(login);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUserById(@PathVariable Long id){
        logger.info("[DELETE] /users/" + id);
        usersService.deleteUser(id);
    }

    @PostMapping(value = "/playlists/{username}")
    public Playlist createPlaylist(@RequestBody Playlist playlist, @PathVariable String username){
        logger.info("[POST] /playlist/" + username, playlist);
        return usersService.createPlaylist(playlist, username);
    }

    @GetMapping(value = "/playlists/{id}")
    public Playlist getPlaylist(@PathVariable Long id){
        logger.info("[GET] /playlists/" + id);
        return usersService.getPlaylist(id);
    }

    @DeleteMapping(value = "/playlists/{id}")
    public void deletePlaylistById(@PathVariable Long id){
        logger.info("[DELETE] /playlists/" + id);
        usersService.deletePlaylist(+id);
    }

    @PostMapping(value = "/playlists/{id}/{song_id}")
    public ResponseEntity addSong(@PathVariable Long id, @PathVariable Long song_id){
        logger.info("[POST] /playlist/" + id + "/" + song_id);

        return usersService.addSong(id, song_id);
    }

    @DeleteMapping(value = "/playlists/{id}/{song_id}")
    public ResponseEntity deleteSong(@PathVariable Long id, @PathVariable Long song_id){
        logger.info("[DELETE] /playlists/" + id + "/" + song_id);

        return usersService.deleteSong(id, song_id);
    }

    @GetMapping(value = "/songs")
    public ResponseEntity getAllSongs(){
        logger.info("[GET] /songs");
        return usersService.getAllSongs();
    }

    @GetMapping(value = "/{username}/playlists")
    public List<Playlist> getPlaylists(@PathVariable String username){
        logger.info("[GET] /" + username + "/playlists");
        return usersService.getPlaylists(username);
    }

    @GetMapping(value = "/search")
    public List<Song> searchSongs(@RequestParam String name){
        logger.info("[GET] /search?name=" + name);
        return usersService.search(name);
    }

    @GetMapping(value = "/search_artists")
    public List<Artist> searchArtists(@RequestParam String name){
        logger.info("[GET] /search_artist?name=" + name);
        return usersService.searchArtists(name);
    }

    @GetMapping(value = "/artist/{id}")
    public Artist getArtist(@PathVariable Long id){
        logger.info("[GET] /artist/" + id);
        return usersService.getArtist(id);
    }

    @GetMapping(value = "/album/{id}")
    public Album getAlbum(@PathVariable Long id){
        logger.info("[GET] /album/" + id);
        return usersService.getAlbum(id);
    }

    @GetMapping(value = "/artist")
    public List<Artist> getArtist(){
        logger.info("[GET] /artist");
        return usersService.getArtists();
    }

    @GetMapping(value = "/healthcheck")
    public ResponseEntity healthCheck(){
        logger.info("[GET] /healthcheck");
        return usersService.healthCheck();
    }

}
