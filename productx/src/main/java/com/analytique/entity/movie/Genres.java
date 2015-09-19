package com.analytique.entity.movie;

import org.springframework.data.annotation.Id;

public class Genres {

    @Id
    String genreId;

    String genreName;

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
