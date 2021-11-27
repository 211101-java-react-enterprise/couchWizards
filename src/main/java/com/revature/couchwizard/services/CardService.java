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

    public boolean isCardValid(Card card) {
            //TODO Write Valid Card Checks
        return true;
    }
}