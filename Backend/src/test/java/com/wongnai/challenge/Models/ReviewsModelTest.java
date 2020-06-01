package com.wongnai.challenge.Models;

import com.wongnai.challenge.Mocks.Services.ReviewsServiceMock;
import com.wongnai.challenge.Services.ReviewsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ReviewsModelTest {

    @Mock
    ReviewsService reviewsService;

    @InjectMocks
    ReviewsModel reviewsModel;

    private ReviewsServiceMock reviewsServiceMock = new ReviewsServiceMock();

    @Test
    void getReviewByIdWithValidId() {
        Mockito.when(reviewsService.getReviewByIdQuery(1)).
                thenReturn(reviewsServiceMock.getReviewByIdQuery(1));

        String expectedOutput = "{\"review\":\"Yay, review exists!\",\"id\":1}";

        assertEquals(expectedOutput, reviewsModel.getReviewById(1));
    }

    @Test
    void getReviewByIdWithInvalidId() {
        Mockito.when(reviewsService.getReviewByIdQuery(0)).
                thenReturn(reviewsServiceMock.getReviewByIdQuery(0));

        assertEquals(null, reviewsModel.getReviewById(0));
    }

    @Test
    void searchReviewByFoodTextWithQueryWithReviews() {
        Mockito.when(reviewsService.searchReviewByFoodTextQuery("Pizza")).
                thenReturn(reviewsServiceMock.searchReviewByFoodTextQuery("Pizza"));

        String expectedOutput = "[{\"review\":\"Exceptional Service!\",\"id\":2},{\"review\":\"Exceptional Service!\",\"id\":2}]";

        assertEquals(expectedOutput, reviewsModel.searchReviewByFoodText("Pizza"));
    }

    @Test
    void searchReviewByFoodTextWithQueryWithNoReviews() {
        Mockito.when(reviewsService.searchReviewByFoodTextQuery("Not in review")).
                thenReturn(reviewsServiceMock.searchReviewByFoodTextQuery("Not in review"));

        assertEquals(null, reviewsModel.searchReviewByFoodText("Not in review"));
    }

    @Test
    void editReviewWithValidIdAndValidJsonQuery() {
        String jsonReview = "{\"review\": \"Really loved the food!\", \"id\": 1}";

        Mockito.when(reviewsService.updateReview(1, "Really loved the food!")).
                thenReturn(reviewsServiceMock.updateReview(1, "Really loved the food!"));

        assertEquals(true, reviewsModel.editReview(1, jsonReview));
    }

    @Test
    void editReviewWithValidIdAndInValidJsonQuery() {
        assertEquals(false, reviewsModel.editReview(1, "Really loved the food!"));
    }

    @Test
    void editReviewWithValidIdAndEmptyJsonQuery() {
        assertEquals(false, reviewsModel.editReview(1,""));
    }

    @Test
    void editReviewWithInvalidIdAndValidJsonQuery() {
        String jsonReview = "{\"review\": \"Food was tasty but slightly pricey.\", \"id\": 1}";

        Mockito.when(reviewsService.updateReview(0, "Food was tasty but slightly pricey.")).
                thenReturn(reviewsServiceMock.updateReview(0, "Food was tasty but slightly pricey."));

        assertEquals(false, reviewsModel.editReview(0, jsonReview));
    }
}