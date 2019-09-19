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
@Table(name = "artist")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rightholder_id")
    @JsonBackReference
    private Rightholder rightholder;

    @Column(name = "name")
    private String name;

    //@Lob
    @Column(name = "info"  /*, columnDefinition = "LONGTEXT"*/)
    private String info;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "artist")
    @JsonManagedReference
    private List<Album> albums;

    public Long getId() {
        return id;
    }

    public Rightholder getRightholder() {
        return rightholder;
    }

    public void setRightholder(Rightholder rightholder) {
        this.rightholder = rightholder;
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

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        return new EqualsBuilder()
                .append(id, artist.id)
                .append(rightholder, artist.rightholder)
                .append(name, artist.name)
                .append(info, artist.info)
                .append(albums, artist.albums)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(rightholder)
                .append(name)
                .append(info)
                .append(albums)
                .toHashCode();
    }

//    @Override
//    public String toString() {
//        return "Artist{" +
//                "id=" + id +
//                ", rightholder=" + rightholder +
//                ", name='" + name + '\'' +
//                ", info='" + info + '\'' +
//                ", albums=" + albums +
//                '}';
//    }
}


