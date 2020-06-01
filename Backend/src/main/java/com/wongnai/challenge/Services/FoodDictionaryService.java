package com.wongnai.challenge.Services;

import com.wongnai.challenge.Entities.FoodDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodDictionaryService extends JpaRepository<FoodDictionary, Integer> {

    @Query(value = "SELECT COUNT(id) FROM FoodDictionary\n" +
            "WHERE MATCH(keyword) AGAINST(?1 IN BOOLEAN MODE)\n" +
            "AND keyword like ?1", nativeQuery = true)
    int checkIfKeywordExistsQuery(String query);
}
