package com.revature.couchwizard;

import com.revature.couchwizard.daos.CardDAO;
import com.revature.couchwizard.models.Card;

import java.util.LinkedList;
import java.util.List;

public class playgroundDriver {
    public static void main(String[] args) {

        Object newCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        Object newLotus = new Card("Black Lotus", 11.18, "Artifact", null, null, null, "Sacrifice Black Lotus: Add three mana of any one color.", "Q", "Vintage Masters");
        Object freeLotus = new Card("Black Lotus", null, "Artifact", null, null, null, "Sacrifice Black Lotus: Add three mana of any one color.", "Q", "Vintage Championship #2017NA");
        Object uLotus = new Card("Black Lotus", null, null, null, null, null, null, "0", null);
        Object qCard = new Card(null, null, null, null, null, null, null, "Q", null);
        CardDAO newDao = new CardDAO();

        /*
        // Save test

        try{newDao.genSave(newCard);}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Select test

        List<Object> testList = new LinkedList<>();
        try{testList = newDao.genRead(qCard);
            System.out.println(testList);}
        catch (Exception e) { e.printStackTrace();}

        // Delete test
        List<Object> dTestList = new LinkedList<>();
        try{dTestList = newDao.genDelete(newCard);
            System.out.println(dTestList);}
        catch (Exception e) { e.printStackTrace();}

        // Update test
        try {newDao.genUp(uLotus);}
        catch(Exception e) { e.printStackTrace();}
        */

        try{
            Card dummyCard = new Card();
            System.out.println();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
