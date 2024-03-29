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
import java.io.PrintWriter;


// Annotation that sets the path for browser routing.
public class InsertCardServlet extends HttpServlet {

    // Card service handles cards.
    // Object mapper works for JSON formatting.
    private final CardService cardService;
    private final ObjectMapper mapper;


    // Constructor
    public InsertCardServlet(CardService cardService, ObjectMapper mapper){
        this.cardService = cardService;
        this.mapper = mapper;
        System.out.println("Insert servlet created!");
    }


    // Overriding the original doPost in a way that sets everything we're doing as a json string.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter respWriter = resp.getWriter();
        resp.setContentType("application/json");



        System.out.println("[CouchWizard] [INFO] Inside servlet!");

        try{
            // Create a new card
            Card newCard = mapper.readValue(req.getInputStream(), Card.class);
            boolean wasSaved = cardService.createNewCard(newCard);
            // Check if the card saved properly in the previous statement.
            if (wasSaved){
                System.out.println("[CouchWizard] [INFO] Card Saved to Database!");
                resp.setStatus(201);
            } else {
                System.out.println("[CouchWizard] [ERROR] Could not save card. Please Check Logs");
                resp.setStatus(500);
            }
        } // Exception for if something goes wrong while trying to save the card.
        catch(JsonParseException e){
            resp.setStatus(400);
            e.printStackTrace();

        }



    }
    /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>/test works</h>");
        System.out.println("Inside test!");
    } */

}
