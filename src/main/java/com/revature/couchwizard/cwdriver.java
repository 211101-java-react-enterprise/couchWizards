package com.revature.couchwizard;

import com.revature.couchwizard.daos.CardDAO;
import com.revature.couchwizard.models.Card;

import java.util.LinkedList;
import java.util.List;

public class cwdriver {
    public static void main(String[] args) {

        Object newCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        Object newLotus = new Card("Black Lotus", 11.18, "Artifact", null, null, null, "Sacrifice Black Lotus: Add three mana of any one color.", "Q", "Vintage Masters");
        Object freeLotus = new Card("Black Lotus", null, "Artifact", null, null, null, "Sacrifice Black Lotus: Add three mana of any one color.", "Q", "Vintage Championship #2003");
        Object qCard = new Card(null, null, null, null, null, null, null, "Q", null);
        CardDAO newDao = new CardDAO();
        try{newDao.genSave(freeLotus);}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<Object> testList = new LinkedList<>();
        try{testList = newDao.genRead(qCard);
            System.out.println(testList);}
        catch (Exception e) { e.printStackTrace();}
    }
}
