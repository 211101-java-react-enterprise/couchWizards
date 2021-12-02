package com.revature.couchwizard.services;

import com.revature.cardorm.ORM.ORM;
import com.revature.couchwizard.exceptions.InvalidRequestException;
import com.revature.couchwizard.exceptions.ResourcePersistenceException;
import com.revature.couchwizard.exceptions.TestingError;
import com.revature.couchwizard.models.Card;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

public class CardServiceTest {
    ORM mockORM;
    CardService sut;

    @Before
    public void testCaseSetup() {
        mockORM = mock(ORM.class);
        sut = new CardService(mockORM);
    }

    @After
    public void testCaseCleanUp(){sut = null;}

    // New card
    @Test
    public void test_newCard_returnsTrue_givenValidCard(){
        // Arrange
        Card validCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");

        // Act
        boolean actualResult = sut.createNewCard(validCard);

        // Assert
        Assert.assertTrue("Expected user to be considered valid", actualResult);
    }

    @Test(expected = InvalidRequestException.class)
    public void test_newCard_throwsError_givenInvalidCard() throws Exception{
        // Arrange
        Card invalidCard = new Card(null, 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        Card invalidCard2 = new Card("name", 1000.00, null, null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        Card invalidCard3 = null;
        Card invalidCard4 = new Card("name", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", null, "OneTooMany");
        Card invalidCard5 = new Card("name", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", null);

        try {
            boolean actualResult = sut.createNewCard(invalidCard);
            actualResult = sut.createNewCard(invalidCard2);
            actualResult = sut.createNewCard(invalidCard3);
            actualResult = sut.createNewCard(invalidCard4);
            actualResult = sut.createNewCard(invalidCard5);
        } finally{
            verify(mockORM, times(0)).genSave(any(Card.class));
        }
    }

    @Test
    public void test_newCard_throwsError_givenBadSQLCAll() throws Exception {
        // Arrange
        Card validCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");

        // Throw an exception from genSave...
        doThrow(Exception.class).when(mockORM).genSave(any(Card.class));
        boolean actualResult = sut.createNewCard(validCard);

    }

    // Find all
    @Test
    public void test_findAllCards_ReturnsList_onNormalCall () throws Exception{
        try{
            sut.findAllCards();
        } finally {
            verify(mockORM,times(1)).genFindAll(any(Card.class));
        }
    }

    @Test
    public void test_findAllCards_ThrowsException_onBadORMCall () throws Exception{
        doThrow(Exception.class).when(mockORM).genFindAll(any());
        sut.findAllCards();
    }

    // Update
    @Test
    public void test_update_returnsTrue_OnValid () throws Exception{
        // Arrange
        Card validCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        when(mockORM.genUp(any(Card.class))).thenReturn(true);
        // Act
        boolean actualResult = sut.updateCard(validCard);

        // Assert
        Assert.assertTrue("Expected user to be considered valid", actualResult);
    }

    @Test (expected = ResourcePersistenceException.class)
    public void test_update_throwsException_OnFalseResult () throws Exception {
        // Arrange
        Card validCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        doAnswer(invocation -> false).when(mockORM).genUp(any());

        boolean actualResult = sut.updateCard(validCard);
    }

    @Test (expected = ResourcePersistenceException.class)
    public void test_update_throwsException_OnBadSQL () throws Exception {
        // Arrange
        Card validCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        doThrow(Exception.class).when(mockORM).genUp(any(Card.class));

        boolean actualResult = sut.updateCard(validCard);
    }

    // Delete
    @Test
    public void test_delete_returnsList_OnValid () throws Exception{
        Card validCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        LinkedList<Card> dummyList = new LinkedList<>();
        dummyList.add(validCard);
        doAnswer(invocation -> dummyList).when(mockORM).genDelete(any());

        LinkedList<Card> target = sut.deleteCards(validCard);
        Assert.assertEquals(dummyList,target);
    }

    @Test
    public void test_delete_outputsException_OnException () throws Exception{
        // Arrange
        Card validCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        LinkedList<Card> dummyList = new LinkedList<>();
        dummyList.add(validCard);
        doThrow(Exception.class).when(mockORM).genDelete(any());

        // Act
        LinkedList<Card> target = sut.deleteCards(validCard);

        // Assert
        Assert.assertEquals(new LinkedList<>(),target);
    }

}
