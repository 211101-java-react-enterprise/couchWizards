package com.revature.couchwizard.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.couchwizard.daos.CardDAO;
import com.revature.couchwizard.services.CardService;
import com.revature.couchwizard.web.servlets.InsertCardServlet;
import com.revature.couchwizard.web.servlets.TestServlet;
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
        CardDAO cardDAO = new CardDAO();
        CardService cardService = new CardService(cardDAO);

        TestServlet testServlet = new TestServlet();
        InsertCardServlet insertCardServlet = new InsertCardServlet(cardService, objectMapper);

        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test/*");
        context.addServlet("InsertCardServlet", insertCardServlet).addMapping("/save");

        System.out.println("Application initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Shutting down Couch Wizards web application!");
    }
}

//TODO Update, Read, Delete Servlets. Test Create(insert) servlet manually with a DB connection. Look into Sessions without a logged in user.