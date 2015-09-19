package com.analytique.entity.movie;

import org.springframework.data.annotation.Id;

public class CastAndCrew {

    @Id
    String castAndCrewId;
    String movieInformationId;
    String personId;
    String rolId;

    public String getCastAndCrewId() {
        return castAndCrewId;
    }

    public void setCastAndCrewId(String castAndCrewId) {
        this.castAndCrewId = castAndCrewId;
    }

    public String getMovieInformationId() {
        return movieInformationId;
    }

    public void setMovieInformationId(String movieInformationId) {
        this.movieInformationId = movieInformationId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }
}
