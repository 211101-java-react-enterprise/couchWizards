package com.revature.couchwizard.services;

import com.revature.couchwizard.models.Card;

import java.util.List;
import com.revature.couchwizard.daos.CardDAO;

public class CardService {

    private final CardDAO cardDAO;

    public CardService(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public List<Card> findAllCards() {
        //TODO Implement Maybe
       // return cardDAO.findAll();
        return null;
    }

    public boolean createNewCard(Card newCard) {

        if (!isCardValid(newCard)) {
            //TODO Exception Logic
            //throw new InvalidRequestException("Invalid card information values provided!");
        }

        Card addedCard = cardDAO.save(newCard);

        if (addedCard == null) {
            //TODO Exception Logic
            //throw new ResourcePersistenceException("The card could not be persisted to the datasource!");
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