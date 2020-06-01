package com.wongnai.challenge.Mocks.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReviewModelsMock {

    public String getReviewByID(long id) {

        // id == 0 to mimic review not found for id specified.
        if (id != 0) {
            JSONObject json = new JSONObject();
            try {
                json.put("id", 1);
                json.put("review", "This is a test review");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return json.toString();
        }
        return null;
    }

    public String searchReviewByFoodText(String query) {

        // query == "Not in review" to mimic no reviews found for keyword.
        if (!"No reviews found".equals(query)) {
            JSONArray jsonArray = new JSONArray();
            JSONObject json = new JSONObject();
            try {
                json.put("id", 1);
                json.put("review", "This review contains <keyword>keyword</keyword>.");
                jsonArray.put(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonArray.toString();
        }
        return null;
    }

    public Boolean editReview(long id, String reviewJson) {

        // id == 0 to mimic review not found for id specified.
        if (id == 0 || reviewJson.isEmpty()) {
            return false;
        }
        return true;
    }


}
