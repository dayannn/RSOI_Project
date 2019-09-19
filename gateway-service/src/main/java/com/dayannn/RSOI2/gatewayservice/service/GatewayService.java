package com.dayannn.RSOI2.gatewayservice.service;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;


public interface GatewayService {
    String getUsers() throws IOException;
    String getUserById(Long userId) throws IOException;
    String getReviewsByUser(Long userId) throws IOException;
    ResponseEntity getBooksWithReviews() throws IOException, JSONException;
    String getReviewsForBook(Long bookId, PageRequest p) throws IOException;
    String getBookById(Long bookId) throws IOException;
    void addUser(String user) throws IOException;
    ResponseEntity createPlaylist(String username, String name) throws IOException;
    ResponseEntity deletePlaylist(Long id) throws IOException;
    ResponseEntity createReview(String review) throws IOException;
    ResponseEntity deleteReview(Long reviewId) throws IOException;
    void deleteUser(Long id) throws IOException;
    HttpResponse requestToken(String url, String credentials) throws IOException;
    HttpResponse checkToken(String url, String token) throws IOException;
    String oauthGetCode(String host, String client_id, String redirect_uri, String response_type) throws IOException;
    String oauthExchangeCode(String host, String code, String redirect_uri, String client_cred) throws IOException;
    ResponseEntity registerUser(String user) throws IOException;
    ResponseEntity getUserId(String username, String clientCredentials) throws IOException;
    ResponseEntity getPlaylists(String username) throws IOException;
    ResponseEntity getPlaylist(Long id) throws IOException;
    HttpResponse play(String path) throws IOException;
    ResponseEntity deleteSongFromPlaylist(Long playlistId, Long songId) throws IOException;
    ResponseEntity addSongToPlaylist(Long playlistId, Long songId) throws IOException;
    ResponseEntity searchSongs(String name) throws IOException;
    ResponseEntity getArtist(Long id) throws IOException;
    ResponseEntity getAlbum(Long id) throws IOException;
    ResponseEntity getArtists() throws IOException;
}
