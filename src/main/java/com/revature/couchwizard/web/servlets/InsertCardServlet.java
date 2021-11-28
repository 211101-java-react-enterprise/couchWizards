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


public class InsertCardServlet extends HttpServlet {

    private final CardService cardService;
    private final ObjectMapper mapper;


    public InsertCardServlet(CardService cardService, ObjectMapper mapper){
        this.cardService = cardService;
        this.mapper = mapper;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>/Insert Works</h>");
        resp.setContentType("application/json");

        try{
            Card newCard = mapper.readValue(req.getInputStream(), Card.class);
            boolean wasSaved = cardService.createNewCard(newCard);
            if (wasSaved){
                System.out.println("Card Saved to Database!");
                resp.setStatus(201);
            } else {
                System.out.println("Could not save card. Please Check Logs");
                resp.setStatus(500);
            }
        }
        catch(JsonParseException e){
            resp.setStatus(400);
            e.printStackTrace();

        }


    }
}
