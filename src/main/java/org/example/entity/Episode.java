package org.example.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Episode implements Serializable {

    private int id;
    private String name, episode;
    private Date airDate;
    private Set<Characters> characters = new HashSet<>(0);

    @Id
    @Column(name = "id")

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // --------------------------------------------------------------

    @Basic
    @Column(name = "name")

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // --------------------------------------------------------------

    @Basic
    @Column(name = "air_date")

    public Date getAirDate() {
        return airDate;
    }
    public void setAirDate(Date airDate) {
        this.airDate = airDate;
    }

    // --------------------------------------------------------------

    @Basic
    @Column(name = "episode")

    public String getEpisode() {
        return episode;
    }
    public void setEpisode(String episode) {
        this.episode = episode;
    }

    // --------------------------------------------------------------

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "character_in_episode",
            joinColumns = @JoinColumn(name = "id_episode"),
            inverseJoinColumns = @JoinColumn(name = "id_character"))

    public Set<Characters> getCharacters() {
        return this.characters;
    }

    public void setCharacters(Set<Characters> characters) {
        this.characters = characters;
    }

    // --------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Episode episode1 = (Episode) o;

        if (id != episode1.id) return false;
        if (!Objects.equals(name, episode1.name)) return false;
        if (!Objects.equals(airDate, episode1.airDate)) return false;
        if (!Objects.equals(episode, episode1.episode)) return false;

        return true;
    }

    // --------------------------------------------------------------

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (airDate != null ? airDate.hashCode() : 0);
        result = 31 * result + (episode != null ? episode.hashCode() : 0);
        return result;
    }
}
