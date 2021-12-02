package com.revature.couchwizard.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.couchwizard.models.Card;
import com.revature.couchwizard.services.CardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ReadCardServlet extends HttpServlet {

    private final CardService cardService;
    private final ObjectMapper mapper;


    public ReadCardServlet(CardService cardService, ObjectMapper mapper){
        this.cardService = cardService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write("<h1>/Read Works</h>");
        resp.setContentType("application/json");
        List<Object> cards;

        System.out.println("[CouchWizard] [INFO] Inside read servlet!");

        cards = cardService.findAllCards();

        if (cards.isEmpty()) {
            resp.setStatus(404); // no cards found
            return; // return here so you don't try to execute the logic after this block
        }

        resp.setStatus(200);
        String payload = mapper.writeValueAsString(cards);
        resp.getWriter().write(payload);
    }
}
