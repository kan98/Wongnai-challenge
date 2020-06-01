package com.wongnai.challenge;

import com.wongnai.challenge.Mocks.Models.FoodDictionaryModelsMock;
import com.wongnai.challenge.Mocks.Models.ReviewModelsMock;
import com.wongnai.challenge.Models.FoodDictionaryModel;
import com.wongnai.challenge.Models.ReviewsModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllersTest {

    @Mock
    ReviewsModel reviewsModel;

    @Mock
    FoodDictionaryModel foodDictionaryModel;

    @InjectMocks
    Controllers controllers;

    private ReviewModelsMock reviewModelsMock = new ReviewModelsMock();
    private FoodDictionaryModelsMock foodDictionaryModelsMock = new FoodDictionaryModelsMock();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetReviewWithEmptyId() throws Exception {
        this.mockMvc.perform(get("/reviews/")
                .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().is(400));
    }

    @Test
    public void testGetReviewWithInvalidId() throws Exception {
        Mockito.when(reviewsModel.getReviewById(0)).thenReturn(reviewModelsMock.getReviewByID(0));

        String expectedOutput = "400 BAD_REQUEST \"400 Bad Request\"";
        try {
            controllers.getReview(new Long(0));
        } catch(Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }

    }

    @Test
    public void testGetReviewWithValidId() {
        Mockito.when(reviewsModel.getReviewById(1)).thenReturn(reviewModelsMock.getReviewByID(1));

        String expectedOutput = "{\"review\":\"This is a test review\",\"id\":1}";

        assertEquals(expectedOutput, controllers.getReview(new Long(1)));
    }

    @Test
    void testGetReviewsWithValidQuery() {
        Mockito.when(foodDictionaryModel.checkIfKeywordExists("keyword")).
                thenReturn(foodDictionaryModelsMock.checkIfKeywordExists("keyword"));
        Mockito.when(reviewsModel.searchReviewByFoodText("keyword")).
                thenReturn(reviewModelsMock.searchReviewByFoodText("keyword"));

        String expectedOutput = "[{\"review\":\"This review contains <keyword>keyword<\\/keyword>.\",\"id\":1}]";

        assertEquals(expectedOutput, controllers.getReviewsWithQuery("keyword"));
    }

    @Test
    void testGetReviewsWithValidQueryWithNoReviews() {
        Mockito.when(foodDictionaryModel.checkIfKeywordExists("No reviews found")).
                thenReturn(foodDictionaryModelsMock.checkIfKeywordExists("No reviews found"));
        Mockito.when(reviewsModel.searchReviewByFoodText("No reviews found")).
                thenReturn(reviewModelsMock.searchReviewByFoodText("No reviews found"));

        String expectedOutput = "";

        assertEquals(expectedOutput, controllers.getReviewsWithQuery("No reviews found"));
    }

    @Test
    void testGetReviewsWithQueryNotInDictionary() {
        Mockito.when(foodDictionaryModel.checkIfKeywordExists("Not Keyword")).
                thenReturn(foodDictionaryModelsMock.checkIfKeywordExists("Not Keyword"));

        String expectedOutput = "400 BAD_REQUEST \"400 Bad Request\"";
        try {
            controllers.getReviewsWithQuery("Not Keyword");
        } catch(Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    @Test
    void editReviewSuccessfully() {
        Mockito.when(reviewsModel.editReview(1, "New review!")).
                thenReturn(reviewModelsMock.editReview(1, "New review!"));

        String expectedOutput = "";

        assertEquals(expectedOutput, controllers.editReview(1, "New review!"));
    }

    void editReviewWithEmptyReview() {
        Mockito.when(reviewsModel.editReview(1, "")).
                thenReturn(reviewModelsMock.editReview(1, ""));

        String expectedOutput = "400 BAD_REQUEST \"400 Bad Request\"";
        try {
            controllers.editReview(1, "");
        } catch(Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }

    void editReviewWithInvalidReviewId() {
        Mockito.when(reviewsModel.editReview(0, "New review!")).
                thenReturn(reviewModelsMock.editReview(0, "New review!"));

        String expectedOutput = "400 BAD_REQUEST \"400 Bad Request\"";
        try {
            controllers.editReview(0, "New review!");
        } catch(Exception e) {
            assertEquals(expectedOutput, e.getMessage());
        }
    }
}