package org.example.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Location implements Serializable {

    private int id;
    private String name, type, dimension;
    private Set<Characters> charactersLocation = new HashSet<>(0);

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
    @Column(name = "type")

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // --------------------------------------------------------------

    @Basic
    @Column(name = "dimension")

    public String getDimension() {
        return dimension;
    }
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    // --------------------------------------------------------------

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idLocation")
    public Set<Characters> getCharactersLocation() {
        return charactersLocation;
    }
    public void setCharactersLocation(Set<Characters> charactersLocation) {
        this.charactersLocation = charactersLocation;
    }

    // --------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (id != location.id) return false;
        if (!Objects.equals(name, location.name)) return false;
        if (!Objects.equals(type, location.type)) return false;
        if (!Objects.equals(dimension, location.dimension)) return false;

        return true;
    }
    // --------------------------------------------------------------

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (dimension != null ? dimension.hashCode() : 0);
        return result;
    }
}
