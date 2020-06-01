package com.wongnai.challenge.Models;

import com.wongnai.challenge.Entities.Reviews;
import com.wongnai.challenge.Services.ReviewsService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ReviewsModel {
    ReviewsService reviewsService;

    public ReviewsModel(ReviewsService reviewsService){
        this.reviewsService = reviewsService;
    }

    public String getReviewById(long id) {
        String review = reviewsService.getReviewByIdQuery(id);

        if (!(review == null) && review.length() > 0) {
            try {
                JSONObject json = new JSONObject();
                json.put("id", id);
                json.put("review", review);
                return json.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String searchReviewByFoodText(String query) {
        List<Reviews> reviews = reviewsService.searchReviewByFoodTextQuery(query);

        if (!(reviews == null) && reviews.size() > 0) {

            JSONArray json = new JSONArray();
            for (Reviews review : reviews) {
                String highlightedReview = review.getReview().
                        replaceAll(query, "&lt;keyword&gt;" + query + "&lt;&#47;keyword&gt;");

                try {
                    JSONObject reviewJson = new JSONObject();
                    reviewJson.put("id", review.getId());
                    reviewJson.put("review", highlightedReview);
                    json.put(reviewJson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return json.toString();
        }
        return null;
    }

    public Boolean editReview(long id, String reviewJson) {
        if (!reviewJson.isEmpty()) {
            try {
                JSONObject reviewObject = new JSONObject(reviewJson);
                String review = reviewObject.getString("review");
                return reviewsService.updateReview(id, review) != 0;
            } catch (JSONException ex) {
                return false;
            }
        }
        return false;
    }
}
