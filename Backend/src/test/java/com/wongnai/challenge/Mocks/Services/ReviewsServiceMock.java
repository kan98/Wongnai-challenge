package com.wongnai.challenge.Mocks.Services;

import com.wongnai.challenge.Entities.Reviews;

import java.util.*;

public class ReviewsServiceMock {

    public String getReviewByIdQuery(long id) {
        // We will use id == 0 to mimic invalid row id, all other id is accepted.

        if (id != 0) {
            return "Yay, review exists!";
        }
        return null;
    }

    public List<Reviews> searchReviewByFoodTextQuery(String query) {
        // We will use query == "Not in review" to mimic query not found in reviews column.
        // All other queries will show as found.

        List<Reviews> reviewsList = new ArrayList<>();
        if (!"Not in review".equals(query)) {
            Reviews reviews = new Reviews();

            reviews.setId(1);
            reviews.setReview("Food was good.");
            reviewsList.add(0, reviews);

            reviews.setId(2);
            reviews.setReview("Exceptional Service!");
            reviewsList.add(1, reviews);
        }
        return reviewsList;
    }

    public int updateReview(long id, String review) {
        // We will use id == 0 to mimic invalid row id where review won't be updated;
        // All other id value is accepted;

        return (int) id;
    }

}