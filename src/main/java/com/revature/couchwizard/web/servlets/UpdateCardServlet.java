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

@WebServlet("/update")
public class UpdateCardServlet extends HttpServlet {

    private final CardService cardService;
    private final ObjectMapper mapper;


    public UpdateCardServlet(CardService cardService, ObjectMapper mapper){
        this.cardService = cardService;
        this.mapper = mapper;
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>/Update Works</h>");
        resp.setContentType("application/json");

        try{
            Card updateCard = mapper.readValue(req.getInputStream(), Card.class);
            boolean wasUpdated = cardService.createNewCard(updateCard);
            if (wasUpdated){
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