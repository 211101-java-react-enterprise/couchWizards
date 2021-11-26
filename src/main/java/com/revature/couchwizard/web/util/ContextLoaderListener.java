package com.revature.couchwizard.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.couchwizard.daos.CardDAO;
import com.revature.couchwizard.web.servlets.TestServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class ContextLoaderListener implements ServletContextListener {

    private final static Logger logger = LogManager.getLogger();
//

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Initializing application");

//        logger.info("Initializing application");

        ObjectMapper objectMapper = new ObjectMapper();
        CardDAO cardDAO = new CardDAO();

       // UserService userService = new UserService(userDAO);

       // FlashcardDAO cardDAO = new FlashcardDAO();
       // FlashcardService cardService = new FlashcardService(cardDAO, userService);

       // FlashcardServlet cardServlet = new FlashcardServlet(cardService, objectMapper);
       // UserServlet userServlet = new UserServlet(userService, objectMapper);

        TestServlet testServlet = new TestServlet();

        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test/*");
        //context.addServlet("FlashcardServlet", cardServlet).addMapping("/flashcards");
       // context.addServlet("UserServlet", userServlet).addMapping("/users/*");

        System.out.println("Application initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Shutting down Couch Wizards web application!");
    }
}

