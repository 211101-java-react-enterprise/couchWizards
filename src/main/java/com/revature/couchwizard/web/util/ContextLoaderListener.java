package com.revature.couchwizard.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.cardorm.ORM.ORM;
import com.revature.couchwizard.services.CardService;
import com.revature.couchwizard.web.servlets.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class ContextLoaderListener implements ServletContextListener {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Initializing application");


        ObjectMapper objectMapper = new ObjectMapper();
        ORM cardDAO = new ORM();
        CardService cardService = new CardService(cardDAO);

        TestServlet testServlet = new TestServlet();
        InsertCardServlet insertCardServlet = new InsertCardServlet(cardService, objectMapper);
        ReadCardServlet readCardServlet = new ReadCardServlet(cardService, objectMapper);
        DeleteCardServlet deleteCardServlet = new DeleteCardServlet(cardService, objectMapper);
        UpdateCardServlet updateCardServlet = new UpdateCardServlet(cardService, objectMapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test");
        context.addServlet("InsertCardServlet", insertCardServlet).addMapping("/save/*");
        context.addServlet("ReadCardServlet", readCardServlet).addMapping("/read/*");
        context.addServlet("DeleteCardServlet", deleteCardServlet).addMapping("/delete/*");
        context.addServlet("UpdateCardServlet", updateCardServlet).addMapping("/update/*");

        System.out.println("Application initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Shutting down Couch Wizards web application!");
    }
}

//TODO Update, Read, Delete Servlets. Test Create(insert) servlet manually with a DB connection. Look into Sessions without a logged in user.