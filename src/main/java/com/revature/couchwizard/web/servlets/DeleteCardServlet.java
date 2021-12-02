package com.revature.couchwizard.web.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.couchwizard.models.Card;
import com.revature.couchwizard.services.CardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


public class DeleteCardServlet extends HttpServlet {

    private final CardService cardService;
    private final ObjectMapper mapper;


    public DeleteCardServlet(CardService cardService, ObjectMapper mapper){
        this.cardService = cardService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        System.out.println("[CouchWizard] [INFO] Inside Delete servlet!");

        LinkedList<Card> wasDeleted = new LinkedList<>();

        try{
            // Delete a Card
            Card removeCard = mapper.readValue(req.getInputStream(), Card.class);
            wasDeleted = cardService.deleteCards(removeCard);
            // Check if the card saved properly in the previous statement.
            if (wasDeleted != null){
                System.out.println("[CouchWizard] [INFO] Card removed from Database!");
                resp.setStatus(200);
            } else {
                System.out.println("[CouchWizard] [ERROR] Could not remove card. Please Check Logs");
                resp.setStatus(500);
            }
        } // Exception for if something goes wrong while trying to save the card.
        catch(JsonParseException e){
            resp.setStatus(400);
            e.printStackTrace();

        }

        String payload = mapper.writeValueAsString(wasDeleted);
        resp.getWriter().write(payload);


    }
}
