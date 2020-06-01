package com.wongnai.challenge.Mocks.Services;

public class FoodDictionaryServiceMock {
    public int checkIfKeywordExistsQuery(String query) {
        // query == "Doesn't exist" to mimic query that doesn't exist
        // All other strings will be accepted by this mock.

        if ("Doesn't exist".equals(query)) {
            return 0;
        }
        return 1;
    }
}
