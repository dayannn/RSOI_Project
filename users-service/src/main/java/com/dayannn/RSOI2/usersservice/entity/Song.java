package com.dayannn.RSOI2.usersservice.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference
    private Album album;

//    @Transient
//    private String artistName;
//
//    @Transient
//    private Long artistId;

    @Column(name = "name")
    private String name;

    @Column(name = "file_path")
    private String filePath;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "playlist_song",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "song_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> playlists;

    public Long getId() {
        return id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getArtistId() {
        return album.getArtist().getId();
    }

    public String getArtistName() {
        return album.getArtist().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return new EqualsBuilder()
                .append(id, song.id)
                .append(album, song.album)
                .append(name, song.name)
                .append(filePath, song.filePath)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(album)
                .append(name)
                .append(filePath)
                .toHashCode();
    }

//    @Override
//    public String toString() {
//        return "Song{" +
//                "id=" + id +
//                ", album=" + album +
//                ", name='" + name + '\'' +
//                ", filePath='" + filePath + '\'' +
//                '}';
//    }
}
