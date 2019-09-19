package com.dayannn.RSOI2.usersservice.service;

import com.dayannn.RSOI2.usersservice.controller.UsersServiceController;
import com.dayannn.RSOI2.usersservice.entity.*;
import com.dayannn.RSOI2.usersservice.exception.UserNotFoundException;
import com.dayannn.RSOI2.usersservice.repository.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final RightholderRepository rightholderRepository;
    private final SongRepository songRepository;

    private Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    public UsersServiceImpl(UserRepository userRepository, PlaylistRepository playlistRepository,
                            ArtistRepository artistRepository, AlbumRepository albumRepository,
                            RightholderRepository rightholderRepository, SongRepository songRepository) {

        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.rightholderRepository = rightholderRepository;
        this.songRepository = songRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) throws UserNotFoundException{
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User findUserByLogin(String login) throws UserNotFoundException{
        User user = userRepository.findByLogin(login);
        if (user == null)
            throw  new UserNotFoundException(login);
        return user;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public Rightholder createRightholder(Rightholder rightholder) {
        return rightholderRepository.save(rightholder);
    }

    @Override
    public Rightholder findRightholderById(Long id) {
        return rightholderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rightholder> getAllRightholders() {
        return rightholderRepository.findAll();
    }

    @Override
    public void deleteRightholder(Long id) {
        rightholderRepository.deleteById(id);
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist createPlaylist(Playlist playlist, String username) {
        User user = userRepository.findByLogin(username);
        playlist.setUser(user);
        return playlistRepository.saveAndFlush(playlist);
    }

    @Override
    public Playlist getPlaylist(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    @Override
    public Playlist findPlaylistById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Song addSong(Song song) {
        return songRepository.saveAndFlush(song);
    }

    @Override
    public Song findSongById(Long id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity addSong(Long id, Long song_id) {
        Playlist playlist = playlistRepository.getOne(id);
        playlist.getSongs().add(songRepository.getOne(song_id));
        playlistRepository.save(playlist);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity deleteSong(Long id, Long song_id) {
        logger.info("inside deleteSong in  UsersService");
        Playlist playlist = playlistRepository.getOne(id);
        logger.info("playlist= " + playlist.getSongs().toString());
        playlist.getSongs().remove(songRepository.getOne(song_id));
        logger.info("playlist after delete = " + playlist.getSongs().toString());
        playlistRepository.saveAndFlush(playlist);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity getAllSongs() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Artist createArtst(Artist artist) {
        return artistRepository.saveAndFlush(artist);
    }

    @Override
    public Artist findArtistById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.saveAndFlush(album);
    }

    @Override
    public Album findAlbumById(Long id) {
        return albumRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public ResponseEntity healthCheck() {
        return ResponseEntity.ok("The usersService is up");
    }

    @Override
    public List<Song> search(String name) {
        return songRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public Artist getArtist(Long id) {
        return artistRepository.getOne(id);
    }

    @Override
    public Album getAlbum(Long id) {
        return albumRepository.getOne(id);
    }

    @Override
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    @Override
    public List<Playlist> getPlaylists(String username) {
        User user = userRepository.findByLogin(username);
        return playlistRepository.findByUser(user);
    }
}
