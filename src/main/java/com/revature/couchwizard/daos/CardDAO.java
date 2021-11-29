package com.revature.couchwizard.daos;

import com.revature.couchwizard.annotations.*;
import com.revature.couchwizard.models.Card;
import com.revature.couchwizard.util.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.UUID;
import java.util.*;
import java.util.stream.Collectors;

public class CardDAO implements CrudDAO<Card>{

    // Able to take any object that's prooperyl annotated (Tables + Columns + Id) and returns if it succeeded.
    public boolean genSave (Object target) throws Exception {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            // Check that we have something to work with first.
            if (target == null) throw new NullPointerException("Given object is null!");

            // Create a generic class that we can reference to for reflection.
            Class<?> targetClass = target.getClass();
            // Then check that it's got a table name
            if(!targetClass.isAnnotationPresent(Table.class)){
                throw new RuntimeException("The class " + targetClass.getSimpleName() + " is not annotated with a table!");
            }
            // Boolean flag to check that we have an id
            boolean doesIdExist = false;
            // See if the existant primary id is the default value of '' or if it has a value. If it is '' then fill it.
            Map<String, String> objMap = new HashMap<>();
            // The for loop reflects the targetClass's fields we referenced and makes them accessible.
            for (Field field: targetClass.getDeclaredFields()){
                field.setAccessible(true); // Accessible moment

                // Checks that we have a valid UUID (Which will have exactly 36 characters)
                if (field.isAnnotationPresent(Id.class)){
                    doesIdExist = true; // Update our flag
                    // We don't want to call tostring on a null value
                    if (field.get(target) != null)
                    {   // UUID's always have 36 characters
                        if (field.get(target).toString().length() != 36 ) objMap.put(field.getAnnotation(Id.class).columnName(), UUID.randomUUID().toString());
                        else objMap.put(field.getAnnotation(Id.class).columnName(), field.get(target).toString());
                    } // .getAnnotation(AnnoName.class) references the annotation which we can then pull .columnName() from.
                    else objMap.put(field.getAnnotation(Id.class).columnName(), UUID.randomUUID().toString());
                } // If it's a normal column, adds that as well
                else if (field.isAnnotationPresent(Column.class)){
                    if (field.get(target) == null) objMap.put(field.getAnnotation(Column.class).columnName(), "null");
                    else objMap.put(field.getAnnotation(Column.class).columnName(), field.get(target).toString());
                }
            }
            if (!doesIdExist) throw new RuntimeException("The class " + targetClass.getSimpleName() + " does not have a primary key!");

            // Build our string out.
            String sql = "insert into ";
            sql = sql + target.getClass().getAnnotation(Table.class).tableName() + " (";
            // Obligatory stream
            String sqlColumn = objMap.entrySet().stream().map(entry -> entry.getKey()).collect(Collectors.joining(", "));
            sql = sql + sqlColumn + ") values ( '";
            sqlColumn = objMap.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.joining("', '"));
            sql = sql + sqlColumn + "')";
            PreparedStatement genSQL = conn.prepareStatement(sql);

            int rowsInserted = genSQL.executeUpdate();

            if (rowsInserted != 0) {
                return true;
            }

    }catch (SQLException e) {
        e.printStackTrace();
    }
        return false;
    }

    public List<Object> genRead(Object target) throws Exception{
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            // Check that we have something to work with first.
            if (target == null) throw new NullPointerException("Given object is null!");

            // Create a generic class that we can reference to for reflection.
            Class<?> targetClass = target.getClass();
            // Then check that it's got a table name
            if(!targetClass.isAnnotationPresent(Table.class)){
                throw new RuntimeException("The class " + targetClass.getSimpleName() + " is not annotated with a table!");
            }
            // See if the existant primary id is the default value of '' or if it has a value. If it is '' then fill it.
            Map<String, String> objMap = new HashMap<>();
            // The for loop reflects the targetClass's fields we referenced and makes them accessible.
            for (Field field: targetClass.getDeclaredFields()){
                field.setAccessible(true); // Accessible moment

                // Checks that we have a valid UUID (Which will have exactly 36 characters)
                if (field.isAnnotationPresent(Id.class)){
                    // We don't want to call tostring on a null value
                    if (field.get(target) != null)
                    {   // Saves the id to the map if it's given.
                        objMap.put(field.getAnnotation(Id.class).columnName(), field.get(target).toString());
                    } // .getAnnotation(AnnoName.class) references the annotation which we can then pull .columnName() from.

                }
                else if (field.isAnnotationPresent(Column.class)){ // Saves any fields given to query to the map
                    if (field.get(target) != null) objMap.put(field.getAnnotation(Column.class).columnName(), field.get(target).toString());
                }
            }

            // Build our string out.
            String sql = "select * from ";
            sql = sql + target.getClass().getAnnotation(Table.class).tableName() + " where ";
            // Obligatory stream
            String sqlColumn = objMap.entrySet().stream().map(entry -> entry.getKey() + " = '" + entry.getValue() + "'").collect(Collectors.joining(" and "));
            sql = sql + sqlColumn;
            System.out.println(sql);

            PreparedStatement genSQL = conn.prepareStatement(sql);

            ResultSet queryResult = genSQL.executeQuery();

            List<Object> queryList = new LinkedList<>();
            // TODO: Figure out how to cast big decimal to double when it needs to be cast
            while (queryResult.next()) {
                Object tempObj = targetClass.newInstance();
                for (Field field: targetClass.getDeclaredFields()){
                    field.setAccessible(true); // Accessible moment
                    if (field.isAnnotationPresent(Id.class)){
                        field.set(tempObj, queryResult.getObject(field.getAnnotation(Id.class).columnName()));
                    }
                    else if (field.isAnnotationPresent(Column.class)){ // Saves any fields given to query to the map
                        field.set(tempObj, queryResult.getObject(field.getAnnotation(Column.class).columnName()));
                    }
                }
                queryList.add(tempObj);
            }

            if (!queryList.isEmpty()) {
                return queryList;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

        List<Card> cards = new LinkedList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            // Create the query
            String sql = "select * from cards";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Card card = new Card();
                card.setId(rs.getString("card_id"));
                card.setName(rs.getString("card_name"));
                card.setPrintSet(rs.getString("print_set"));
                card.setColor(rs.getString("c_cost"));
                card.setSuperTypes(rs.getString("supertype"));
                card.setPower(rs.getString("c_power"));
                card.setToughness(rs.getString("c_tough"));
                card.setDescription(rs.getString("c_desc"));
                card.setValue(rs.getDouble("value"));
                cards.add(card);
            }

            return cards;


        } catch (SQLException e) {
            e.printStackTrace();
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
