package com.dayannn.RSOI2.gatewayservice.controller;

import com.dayannn.RSOI2.gatewayservice.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/api")
public class GatewayController {
    private final GatewayService gatewayService;
    final private String authServiceUrl = "http://localhost:8081";
    private Logger logger = LoggerFactory.getLogger(GatewayController.class);

    @Autowired
    public GatewayController(GatewayService gatewayService){
        this.gatewayService = gatewayService;
    }

    private HttpResponse isTokenValid(String token) throws IOException {
        token = token.replace("Bearer ", "");
        return gatewayService.checkToken(authServiceUrl, token);
    }

    @GetMapping(path = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUsers(@RequestHeader("Authorization") String token) throws IOException{
        logger.info("[GET] users/");
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return ResponseEntity.ok(gatewayService.getUsers());
    }

    @DeleteMapping(path = "users/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId, @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[DELETE] users/" + userId);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        gatewayService.deleteUser(userId);
        return ResponseEntity.ok("");
    }

    @PostMapping(path = "users")
    public ResponseEntity addUser(@RequestBody String user, @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[POST] /users\n ", user);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        gatewayService.addUser(user);
        return ResponseEntity.ok("");
    }

    @PostMapping(path = "playlists")
    public ResponseEntity addPlaylist(@RequestBody String name, @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[POST] /playlists, name= ", name);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }else {
            String username = getUsernameFromResponse(response.getEntity());
            if (username == null){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("error getting username from token");
            }
            return gatewayService.createPlaylist(username, name);
        }
    }

    @DeleteMapping(path = "playlists/{id}")
    public ResponseEntity deletePlaylist(@PathVariable Long id, @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[DELETE] /playlists/" + id);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.deletePlaylist(id);
    }

    @GetMapping(path = "users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserById(@PathVariable Long userId, @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[GET] users/" +userId);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return ResponseEntity.ok(gatewayService.getUserById(userId));
    }

    @GetMapping(path = "/users/{userId}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReviewsByUser(@PathVariable Long userId, @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[GET] /users/" + userId + "/reviews");
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return ResponseEntity.ok(gatewayService.getReviewsByUser(userId));
    }

    @GetMapping(path = "/book/{bookId}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getReviewsForBook(@PathVariable Long bookId,
                                    @RequestParam (value = "page") int page,
                                    @RequestParam (value = "size") int size,
                                    @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[GET] /book/" + bookId + "/reviews/?page=" + page + "&size=" + size);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        PageRequest p;
        p = PageRequest.of(page, size);
        return ResponseEntity.ok(gatewayService.getReviewsForBook(bookId, p));
    }

    @GetMapping(path = "book/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBookById(@PathVariable Long bookId, @RequestHeader("Authorization") String token) throws IOException{
        logger.info("[GET] /book/" + bookId);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return ResponseEntity.ok(gatewayService.getBookById(bookId));
    }


    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBooksWithReviews(@RequestHeader("Authorization") String token) throws IOException, JSONException {
        logger.info("[GET] /books");
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.getBooksWithReviews();
    }

    @PostMapping(value = "/reviews")
    public ResponseEntity createReview(@RequestBody String review, @RequestHeader("Authorization") String token) throws IOException {
        logger.info("[POST] /reviews ", "review: ", review);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.createReview(review);
    }

    @DeleteMapping(value = "/reviews/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Long reviewId, @RequestHeader("Authorization") String token) throws IOException {
        logger.info("[DELETE] /reviews/" + reviewId);
        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.deleteReview(reviewId);
    }

    @GetMapping(value = "/playlists")
    public ResponseEntity getUserPlaylists(@RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[GET] /playlists/");

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        } else {
            String username = getUsernameFromResponse(response.getEntity());
            if (username == null){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("error getting username from token");
            }
            return gatewayService.getPlaylists(username);
        }
    }

    @GetMapping(value = "/playlist/{id}")
    public ResponseEntity getPlaylistById(@PathVariable Long id,
                                          @RequestHeader ("Authorization") String token)
            throws IOException {
        logger.info("[GET] /playlist/" + id);

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        } else {
            String username = getUsernameFromResponse(response.getEntity());
            if (username == null){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("error getting username from token");
            }
            return gatewayService.getPlaylist(id);
        }
    }

    private String getUsernameFromResponse(HttpEntity entity) throws IOException {
        String resp = EntityUtils.toString(entity);
        String result = null;
        JSONObject obj = null;
        try {
            obj = new JSONObject(resp);
            result = obj.getString("user_name");
        } catch (JSONException e) {
            return null;
        }

        return result;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestParam(value = "username") String username,
                                @RequestParam(value = "password") String password,
                                @RequestHeader("Authorization") String clientCred) throws IOException{
        logger.info("[POST] /login" +
                " username=" + username +
                ", password= " + password +
                ", credentials= " + clientCred);

        clientCred = clientCred.replace("Basic", "");
        HttpResponse response = gatewayService.requestToken(authServiceUrl +
                "/oauth/token?grant_type=password&username=" + username +
                "&password=" + password,
                clientCred);
        try {
            JSONObject p = new JSONObject(EntityUtils.toString(response.getEntity()));
            String token = p.getString("access_token");

            if (token.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            return ResponseEntity.ok(token);
        }
        catch (JSONException ex){
            logger.error("Error parsing response ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping(path = "/user_id/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserId(@PathVariable(value = "username") String username,
                                    @RequestHeader("Authorization") String clientCred) throws IOException {
        logger.info("[GET] /user_id" +
                " username=" + username);
        return gatewayService.getUserId(username, clientCred);
    }

    @GetMapping(path = "/oauth/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity oauthLogin(
            @RequestParam(value = "clientId") String clientId,
            @RequestParam(value = "redirectUri") String redirectUri) throws IOException {

        logger.info("[GET] /oauth/login" +
                ", clientId= " + clientId +
                ", redirectUri= " + redirectUri);

        String r = gatewayService.oauthGetCode(authServiceUrl, clientId, redirectUri, "code");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", r);
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }


    @GetMapping(path = "/oauth/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity oauthToken(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "redirectUri") String redirectUri,
            @RequestHeader("Authorization") String clientCred) throws IOException{

        logger.info("[GET] /oauth/token" +
                ", code= " + code +
                ", redirectUri= " + redirectUri +
                ", clientCred= " + clientCred);

        clientCred = clientCred.replace("Basic","");
        String r = gatewayService.oauthExchangeCode(authServiceUrl, code, redirectUri, clientCred);

        return ResponseEntity.ok(r);
    }

    @PostMapping(path = "/register")
    public ResponseEntity registerUser(@RequestBody String user) throws IOException {
        logger.info("[POST] /register");
        gatewayService.registerUser(user);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(path = "/playlist/{playlistId}/{songId}")
    public ResponseEntity deleteSongFromPlaylist(@PathVariable Long playlistId,
                                                 @PathVariable Long songId,
                                                 @RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[DELETE] /playlist/" + playlistId + "/" + songId);

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.deleteSongFromPlaylist(playlistId, songId);
    }

    @PostMapping(path = "/playlist/{playlistId}/{songId}")
    public ResponseEntity addSongToPlaylist(@PathVariable Long playlistId,
                                                 @PathVariable Long songId,
                                                 @RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[DELETE] /playlist/" + playlistId + "/" + songId);

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.addSongToPlaylist(playlistId, songId);
    }


    @GetMapping(path = "/search")
    public ResponseEntity searchSongs(@RequestParam String name,
                                      @RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[GET] /search?name=" + name);

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.searchSongs(name);
    }

    @GetMapping(path = "/artsearch")
    public ResponseEntity searchArtists(@RequestParam String name,
                                      @RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[GET] /artsearch?name=" + name);

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.searchArtists(name);
    }

    @GetMapping(path = "/artist/{id}")
    public ResponseEntity getArtist(@PathVariable Long id,
                                      @RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[GET] /artist/" + id);

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.getArtist(id);
    }

    @GetMapping(path = "/album/{id}")
    public ResponseEntity getAlbum(@PathVariable Long id,
                                    @RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[GET] /artist/" + id);

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.getAlbum(id);
    }

    @GetMapping(path = "/artist")
    public ResponseEntity getArtists(@RequestHeader ("Authorization") String token) throws IOException {
        logger.info("[GET] /artist");

        HttpResponse response = isTokenValid(token);
        if (response.getStatusLine().getStatusCode() != org.apache.http.HttpStatus.SC_OK){
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(EntityUtils.toString(response.getEntity()));
        }

        return gatewayService.getArtists();
    }
}
