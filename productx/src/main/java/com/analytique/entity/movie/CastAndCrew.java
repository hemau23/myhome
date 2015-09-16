package com.analytique.entity.movie;

import org.springframework.data.annotation.Id;

public class CastAndCrew {

    @Id
    Long castAndCrewId;

    Long movieInformationId;
    Long personId;
    Long rolId;

    public Long getCastAndCrewId() {
        return castAndCrewId;
    }

    public void setCastAndCrewId(Long castAndCrewId) {
        this.castAndCrewId = castAndCrewId;
    }

    public Long getMovieInformationId() {
        return movieInformationId;
    }

    public void setMovieInformationId(Long movieInformationId) {
        this.movieInformationId = movieInformationId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
}
