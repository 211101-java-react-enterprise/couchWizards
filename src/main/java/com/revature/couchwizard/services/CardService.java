package com.revature.couchwizard.services;

import com.revature.cardorm.ORM.ORM;
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

        try{
           return cardORM.genFindAll(dummyCard);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("oh god we returned null");
       return null; // Should never get here
    }

    public boolean createNewCard(Card newCard) {

        if (!isCardValid(newCard)) {
            //TODO Exception Logic
            //throw new InvalidRequestException("Invalid card information values provided!");
        }
        try {
            Object addedCard = cardORM.genSave(newCard);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;

    }

    public boolean updateCard(Card updateCard) throws Exception{

        if (!isCardValid(updateCard)) {
            //TODO Exception Logic
            //throw new InvalidRequestException("Invalid card information values provided!");
        }

        boolean addedCard = false;

        try {
            addedCard = cardORM.genUp(updateCard);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if (!addedCard) {
            //TODO Exception Logic
            throw new Exception("The card could not be updated in datasource!");
        }

        return true;

    }

    public LinkedList<Card> deleteCards (Card target) {

        //TODO Check Validity Maybe
        //At least one of (the static values or an ID)

        try {
            LinkedList<Object> removedObjects = cardORM.genDelete(target);
            LinkedList<Card> removedCards = new LinkedList<>();
            int i = 0;
            while (i < removedObjects.size()){
                removedCards.add((Card) removedObjects.get(i));
                i++;
            }
            return removedCards;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    // Checks that any card utilized is valid.
    public boolean isCardValid(Card card) {
            //TODO Write Valid Card Checks
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