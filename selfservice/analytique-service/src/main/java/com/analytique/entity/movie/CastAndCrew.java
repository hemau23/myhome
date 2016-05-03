package com.analytique.entity.movie;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CastAndCrew {

    @Id
    String castAndCrewId;
    String movieInformationId;
    String personId;
    String roleId;

    public String getCastAndCrewId() {
        return this.castAndCrewId;
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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
