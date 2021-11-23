package com.revature.couchwizard.daos;

import com.revature.couchwizard.models.card;
import com.revature.couchwizard.util.ConnectionFactory;
import com.revature.couchwizard.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class cardDAO implements CrudDAO<card>{

    public card save(card newCard) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            // Sets a UUID for the card
            newCard.setId(UUID.randomUUID().toString());

            // Pushes the card to the database
            String sql = "insert into cards (card_name, print_set, c_cost, supertype, subtype, c_power, c_tough, c_desc, value, card_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newCard.getName());
            pstmt.setString(2, newCard.getPrintSet());
            pstmt.setString(3, newCard.getColor().toString());
            pstmt.setString(4, newCard.getSuperTypes());
            pstmt.setString(5, newCard.getSubTypes());
            pstmt.setString(6, newCard.getPower());
            pstmt.setString(7, newCard.getDescription());
            pstmt.setDouble(8, newCard.getCost());
            pstmt.setString(9, newCard.getId());

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
    public List<card> findAll() { return null;}

    @Override
    public card findById(String id) {
        return null;
    }

    @Override
    public boolean update(card updatedObj){
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
            pstmt.setDouble(8, updatedObj.getCost());
            pstmt.setString(9, updatedObj.getId());

            // Insert the rows and record any changes to make sure they happen
            int rowsInserted = pstmt.executeUpdate();

            // Return true if there's been any changes
            if (rowsInserted != 0) {
                return true;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
