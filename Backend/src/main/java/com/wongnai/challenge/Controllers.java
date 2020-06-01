package com.wongnai.challenge;

import com.wongnai.challenge.Models.FoodDictionaryModel;
import com.wongnai.challenge.Models.ReviewsModel;
import com.wongnai.challenge.Services.FoodDictionaryService;
import com.wongnai.challenge.Services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class Controllers {
    ReviewsModel reviewsModel;
    FoodDictionaryModel foodDictionaryModel;

    @Autowired
    public Controllers(ReviewsService reviewsService, FoodDictionaryService foodDictionaryService){
        reviewsModel = new ReviewsModel(reviewsService);
        foodDictionaryModel = new FoodDictionaryModel(foodDictionaryService);
    }

    @GetMapping("/reviews/{id}")
    @ResponseBody
    public String getReview(@PathVariable("id") Long id) {
        String output = reviewsModel.getReviewById(id);
        if (output == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "400 Bad Request");
        }
        return output;
    }

    @GetMapping("/reviews")
    @ResponseBody
    public String getReviewsWithQuery(@RequestParam String query) {
        if(foodDictionaryModel.checkIfKeywordExists(query)) {
            String output = reviewsModel.searchReviewByFoodText(query);
            if (output == null) {
                return "";
            } else {
                return output;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "400 Bad Request");
    }

    @PutMapping("/reviews/{id}")
    @ResponseBody
    public String editReview(@PathVariable long id, @RequestBody String review) {
        if (reviewsModel.editReview(id, review)) {
            return "";
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "400 Bad Request");
    }

}
