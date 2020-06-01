package com.wongnai.challenge.Models;

import com.wongnai.challenge.Services.FoodDictionaryService;

public class FoodDictionaryModel {

    FoodDictionaryService foodDictionaryService;

    public FoodDictionaryModel(FoodDictionaryService foodDictionaryService){
        this.foodDictionaryService = foodDictionaryService;
    }

    public Boolean checkIfKeywordExists(String query) {
        int keywordCount = foodDictionaryService.checkIfKeywordExistsQuery(query);
        if (keywordCount > 0) {
            return true;
        }
        return false;
    }
}
