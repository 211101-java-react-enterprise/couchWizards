package com.revature.couchwizard.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.couchwizard.services.CardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteCardServlet extends HttpServlet {

    private final CardService cardService;
    private final ObjectMapper mapper;


    public DeleteCardServlet(CardService cardService, ObjectMapper mapper){
        this.cardService = cardService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>/Delete Works</h>");
        resp.setContentType("application/json");
        //Todo Logic to pull and card from the DB based on conditions
    }
}
