package com.revature.couchwizard.services;

import com.revature.cardorm.ORM.ORM;
import com.revature.couchwizard.exceptions.InvalidRequestException;
import com.revature.couchwizard.exceptions.ResourcePersistenceException;
import com.revature.couchwizard.models.Card;

import java.util.LinkedList;
import java.util.List;


public class CardService {

    private final ORM cardORM;

    public CardService( ORM cardORM) {
        this.cardORM = cardORM;
    }

    public List<Object> findAllCards() {
       Card dummyCard = new Card();
       List <Object> returnList = new LinkedList<>();

        try{
           returnList = cardORM.genFindAll(dummyCard);

        } catch (Exception e) {
            e.printStackTrace();
        }
       return returnList; // Should never get here
    }

    public boolean createNewCard(Card newCard) {

        if (!isCardValid(newCard)) {
            throw new InvalidRequestException("Invalid card information values provided!");
        }
        try {
            Object addedCard = cardORM.genSave(newCard);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;

    }

    public boolean updateCard(Card updateCard){
        boolean addedCard = false;

        try {
            addedCard = cardORM.genUp(updateCard);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if (!addedCard) {
            throw new ResourcePersistenceException("[CouchWizard] [ERROR] The card could not be updated in datasource!");
        }

        return true;

    }

    public LinkedList<Card> deleteCards (Card target) {

        LinkedList<Card> removedCards = new LinkedList<>();

        try {
            LinkedList<Object> removedObjects = cardORM.genDelete(target);
            int i = 0;
            while (i < removedObjects.size()){
                removedCards.add((Card) removedObjects.get(i));
                i++;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return removedCards;
    }

    // Checks that any card utilized is valid.
    public boolean isCardValid(Card card) {

        if (card == null) return false;
        if (card.getName() == null || card.getName().trim().equals("")) return false;
        if (card.getColor() == null || card.getColor().trim().equals("")) return false;
        if (card.getSuperTypes() == null || card.getSuperTypes().trim().equals("")) return false;
        if (card.getPrintSet() == null || card.getPrintSet().trim().equals("")) return false;
        if (card.getSuperTypes().contains("Creature") && card.getPower() == null) return false;
        if (card.getSuperTypes().contains("Creature") && card.getToughness() == null) return false;
        return (card.getName() != null && !card.getSuperTypes().trim().equals("") && !card.getPrintSet().trim().equals(""));
    }
}