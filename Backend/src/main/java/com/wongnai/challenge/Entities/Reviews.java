package com.wongnai.challenge.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reviews {
    @Id
    private long id;

    private String review;

    public long getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
