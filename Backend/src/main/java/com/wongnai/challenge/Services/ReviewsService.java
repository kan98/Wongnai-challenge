package com.wongnai.challenge.Services;

import com.wongnai.challenge.Entities.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;

public interface ReviewsService extends JpaRepository<Reviews, Integer> {

    @Query(value = "SELECT review FROM Reviews WHERE id = ?1", nativeQuery = true)
    String getReviewByIdQuery(long id);

    @Query(value = "SELECT * FROM Reviews\n" +
            "WHERE review like CONCAT('%',?1,'%')", nativeQuery = true)
    List<Reviews> searchReviewByFoodTextQuery(String query);

    @Modifying
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    @Query("UPDATE Reviews SET review = ?2 WHERE id = ?1")
    int updateReview(long id, String review);

}