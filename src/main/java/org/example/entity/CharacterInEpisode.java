package org.example.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "character_in_episode", schema = "public", catalog = "serie")
public class CharacterInEpisode implements Serializable {

    private int idCharacter, idEpisode;

    @Id
    @Column(name = "id_character")

    public int getIdCharacter() {
        return idCharacter;
    }
    public void setIdCharacter(int idCharacter) {
        this.idCharacter = idCharacter;
    }

    @Id
    @Column(name = "id_episode")

    public int getIdEpisode() {
        return idEpisode;
    }
    public void setIdEpisode(int idEpisode) {
        this.idEpisode = idEpisode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterInEpisode that = (CharacterInEpisode) o;

        if (idCharacter != that.idCharacter) return false;
        if (idEpisode != that.idEpisode) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCharacter;
        result = 31 * result + idEpisode;
        return result;
    }
}
