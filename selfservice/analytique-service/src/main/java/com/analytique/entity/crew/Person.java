package com.analytique.entity.crew;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Person {

    @Id
    String personId;

    String name;
    Integer personRating;


    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPersonRating() {
        return personRating;
    }

    public void setPersonRating(Integer personRating) {
        this.personRating = personRating;
    }
}
