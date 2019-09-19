package com.dayannn.RSOI2.usersservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column (name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "playlist_song",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "playlist_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Playlist playlist = (Playlist) o;

        return new EqualsBuilder()
                .append(id, playlist.id)
                .append(user, playlist.user)
                .append(name, playlist.name)
                .append(songs, playlist.songs)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(user)
                .append(name)
                .append(songs)
                .toHashCode();
    }
//
//    @Override
//    public String toString() {
//        return "Playlist{" +
//                "id=" + id +
//                ", user=" + user +
//                ", name='" + name + '\'' +
//                ", songs=" + songs +
//                '}';
//    }
}