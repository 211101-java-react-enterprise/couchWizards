package com.revature.couchwizard.services;

import com.revature.couchwizard.models.card;

import java.util.List;
import com.revature.couchwizard.daos.CardDAO;

public class CardService {

    private final CardDAO cardDAO;

    public CardService(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public List<card> findAllCards() {
        //TODO Implement Maybe
       // return cardDAO.findAll();
        return null;
    }

    public void createNewCard(card newCard) {

        if (!isCardValid(newCard)) {
            //TODO Exception Logic
            //throw new InvalidRequestException("Invalid card information values provided!");
        }

        card addedCard = cardDAO.save(newCard);

        if (addedCard == null) {
            //TODO Exception Logic
            //throw new ResourcePersistenceException("The card could not be persisted to the datasource!");
        }

    }

    public boolean isCardValid(card card) {
            //TODO Write Valid Card Checks
        return true;
    }
}