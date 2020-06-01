package com.wongnai.challenge.Mocks.Models;

public class FoodDictionaryModelsMock {
    public Boolean checkIfKeywordExists(String query) {
        // query == "Not Keyword" is used to mimic query not existing in FoodDictionary table

        if ("Not Keyword".equals(query)) {
            return false;
        }
        return true;
    }
}
