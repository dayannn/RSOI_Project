package com.dayannn.RSOI2.usersservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "album")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonBackReference
    private Artist artist;

    @Column(name = "name")
    private String name;

   // @Lob
    @Column(name = "info" /*, columnDefinition = "LONGTEXT"*/)
    private String info;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album")
    @JsonManagedReference
    private List<Song> songs;

    public Long getId() {
        return id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

        Album album = (Album) o;

        return new EqualsBuilder()
                .append(id, album.id)
                .append(artist, album.artist)
                .append(name, album.name)
                .append(info, album.info)
                .append(songs, album.songs)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(artist)
                .append(name)
                .append(info)
                .append(songs)
                .toHashCode();
    }

//    @Override
//    public String toString() {
//        return "Album{" +
//                "id=" + id +
//                ", artist=" + artist +
//                ", name='" + name + '\'' +
//                ", info='" + info + '\'' +
//                ", songs=" + songs +
//                '}';
//    }
}
