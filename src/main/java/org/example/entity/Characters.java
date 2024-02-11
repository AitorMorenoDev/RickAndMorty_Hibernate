package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.boot.jaxb.Origin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Characters implements Serializable {
    private int id;
    private String name, status, species, type, gender;
    private Set<Episode> episodes = new HashSet<>(0);
    private Location idOrigin = new Location();
    private Location idLocation = new Location();

    public Characters() {
    }

    // --------------------------------------------------------------

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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // --------------------------------------------------------------

    @Basic
    @Column(name = "species")
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    // --------------------------------------------------------------

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // --------------------------------------------------------------

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    // --------------------------------------------------------------

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origin", referencedColumnName = "id")
    public Location getIdOrigin() {
        return this.idOrigin;
    }

    public void setIdOrigin(Location idOrigin) {
        this.idOrigin = idOrigin;
    }

    // --------------------------------------------------------------

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location", referencedColumnName = "id")
    public Location getIdLocation() {
        return this.idLocation;
    }
    public void setIdLocation(Location idLocation) {
        this.idLocation = idLocation;
    }
    // --------------------------------------------------------------

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "character_in_episode",
            joinColumns = @JoinColumn(name = "id_character", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_episode", nullable = false, updatable = false))

    public Set<Episode> getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }

    // --------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Characters character = (Characters) o;

        if (id != character.id) return false;
        if (!Objects.equals(name, character.name)) return false;
        if (!Objects.equals(status, character.status)) return false;
        if (!Objects.equals(species, character.species)) return false;
        if (!Objects.equals(type, character.type)) return false;
        if (!Objects.equals(gender, character.gender)) return false;
        if (!Objects.equals(idOrigin, character.idOrigin)) return false;
        if (!Objects.equals(idLocation, character.idLocation)) return false;

        return true;
    }

    // --------------------------------------------------------------

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (idOrigin != null ? idOrigin.hashCode() : 0);
        result = 31 * result + (idLocation != null ? idLocation.hashCode() : 0);
        return result;
    }
}
