package com.wongnai.challenge.Models;

import com.wongnai.challenge.Mocks.Services.FoodDictionaryServiceMock;
import com.wongnai.challenge.Models.FoodDictionaryModel;
import com.wongnai.challenge.Services.FoodDictionaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class FoodDictionaryModelTest {

    @Mock
    FoodDictionaryService foodDictionaryService;

    @InjectMocks
    FoodDictionaryModel foodDictionaryModel;

    private FoodDictionaryServiceMock foodDictionaryServiceMock = new FoodDictionaryServiceMock();

    @Test
    void checkIfKeywordExistsWithKeywordThatExists() {
        Mockito.when(foodDictionaryService.checkIfKeywordExistsQuery("Keyword")).
                thenReturn(foodDictionaryServiceMock.checkIfKeywordExistsQuery("Keyword"));

        assertEquals(true, foodDictionaryModel.checkIfKeywordExists("Keyword"));
    }

    @Test
    void checkIfKeywordExistsWithKeywordThatDoesntExist() {
        Mockito.when(foodDictionaryService.checkIfKeywordExistsQuery("Doesn't exist")).
                thenReturn(foodDictionaryServiceMock.checkIfKeywordExistsQuery("Doesn't exist"));

        assertEquals(false, foodDictionaryModel.checkIfKeywordExists("Doesn't exist"));
    }
}