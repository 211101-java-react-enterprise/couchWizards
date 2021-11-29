package com.revature.couchwizard;

import com.revature.couchwizard.daos.CardDAO;
import com.revature.couchwizard.models.Card;

import java.util.LinkedList;
import java.util.List;

public class cwdriver {
    public static void main(String[] args) {

        Object newCard = new Card("Doubling Season", 1000.00, "Enchantment", null, null, null, "Some Stuff About Stuff", "GCCC", "OneTooMany");
        CardDAO newDao = new CardDAO();
        //try{newDao.genSave(newCard);}
        List<Object> testList = new LinkedList<>();
        try{testList = newDao.genRead(newCard);
            System.out.println(testList);}
        catch (Exception e) { e.printStackTrace();}
    }
}
