package com.revature.couchwizard.daos;

import com.revature.cardorm.ORM.ORM;
import com.revature.cardorm.annotations.*;
import com.revature.couchwizard.models.Card;
import com.revature.couchwizard.util.ConnectionFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

// RIP TO A REAL ONE
// Kept in case we need to pull it for later. But he's here for now.
public class CardDAO implements CrudDAO<Card>{

    ORM cardORM = new ORM();
    public Card save(Card newCard) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            // Sets a UUID for the card
            newCard.setId(UUID.randomUUID().toString());

            // Pushes the card to the database
            String sql = "insert into cards (card_name, print_set, c_cost, supertype, subtype, c_power, c_tough, c_desc, value, card_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCard.getName());
            pstmt.setString(2, newCard.getPrintSet());
            pstmt.setString(3, newCard.getColor());
            pstmt.setString(4, newCard.getSuperTypes());
            pstmt.setString(5, newCard.getSubTypes());
            pstmt.setString(6, newCard.getPower());
            pstmt.setString(7, newCard.getToughness());
            pstmt.setString(8, newCard.getDescription());
            pstmt.setDouble(9, newCard.getValue());
            pstmt.setString(10, newCard.getId());

            // Insert the rows and record any changes to make sure they happen
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                return newCard;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean removeById(String id) {
        return false;
    }


    @Override
    public List<Card> findAll() {
        Card dummyCard = new Card();
        List<Card> cards = new LinkedList<>();
        List<Object> qCards = new LinkedList<>();
        try {
            qCards =  cardORM.genFindAll(dummyCard);
            int i = 0;
            while (i < qCards.size()){
                cards.add((Card) qCards.get(i));
                i++;
            }
            return cards;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public Card findById(String id) {
        // Decalre an empty card
        Card target = new Card();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Create the query
            String sql = "select * from cards where card_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            // Perform the query
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                target.setId(rs.getString("card_id"));
                target.setName(rs.getString("card_name"));
                target.setPrintSet(rs.getString("print_set"));
                target.setColor(rs.getString("c_cost"));
                target.setSuperTypes(rs.getString("supertype"));
                target.setPower(rs.getString("c_power"));
                target.setToughness(rs.getString("c_tough"));
                target.setDescription(rs.getString("c_desc"));
                target.setValue(rs.getDouble("value"));
                return target;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Updation method
    @Override
    public Card update(Card updatedObj){
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            // Pushes the card to the database
            String sql = "update cards set card_name = ?, print_set = ?, c_cost = ?, supertype = ?, subtype = ?, c_power = ?, c_tough = ?, c_desc = ?, value = ? where card_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, updatedObj.getName());
            pstmt.setString(2, updatedObj.getPrintSet());
            pstmt.setString(3, updatedObj.getColor().toString());
            pstmt.setString(4, updatedObj.getSuperTypes());
            pstmt.setString(5, updatedObj.getSubTypes());
            pstmt.setString(6, updatedObj.getPower());
            pstmt.setString(7, updatedObj.getDescription());
            pstmt.setDouble(8, updatedObj.getValue());
            pstmt.setString(9, updatedObj.getId());

            // Insert the rows and record any changes to make sure they happen
            int rowsInserted = pstmt.executeUpdate();

            // Return true if there's been any changes
            if (rowsInserted != 0) {
                return updatedObj;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Deletion method
    public Card delete(Card targetObj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Create the query to delete anything with the matching UUID
            String sql = "delete from cards * where card_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, targetObj.getId());

            // Perform the command and record the output
            int rowsUpdated = pstmt.executeUpdate();

            // If it succeeds, return the deleted object
            if (rowsUpdated != 0) {
                return targetObj;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
