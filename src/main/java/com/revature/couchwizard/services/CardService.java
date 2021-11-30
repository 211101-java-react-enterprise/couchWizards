package com.revature.couchwizard.services;

import com.revature.couchwizard.models.Card;

import java.util.List;
import com.revature.couchwizard.daos.CardDAO;


public class CardService {

    private final CardDAO cardDAO;

    public CardService(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public List<Object> findAllCards() {
       Card dummyCard = new Card();

        try{
           return cardDAO.genFindAll(dummyCard);

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
            Object addedCard = cardDAO.genSave(newCard);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;

    }

    public boolean updateCard(Card updateCard) {

        if (!isCardValid(updateCard)) {
            //TODO Exception Logic
            //throw new InvalidRequestException("Invalid card information values provided!");
        }

        Card addedCard = cardDAO.update(updateCard);

        if (addedCard == null) {
            //TODO Exception Logic
            //throw new ResourcePersistenceException("The card could not be persisted to the datasource!");
        }

        return true;

    }

    public Card deleteCard (Card target) {

        //TODO Check Validity Maybe
        //At least one of (the static values or an ID)

        try {
            Object removedCard = cardDAO.genDelete(target);
            return (Card) removedCard;
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