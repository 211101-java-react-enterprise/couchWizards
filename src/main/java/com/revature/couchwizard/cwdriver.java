package com.revature.couchwizard;

import com.revature.couchwizard.annotations.Init;
import com.revature.couchwizard.annotations.JsonField;
import com.revature.couchwizard.annotations.JsonTableAnnotation;
import com.revature.couchwizard.daos.CardDAO;
import com.revature.couchwizard.models.Card;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class cwdriver {
    public static void main(String[] args) {

        Object newCard = new Card("Doubling Season", 1000.00, "Enchantment", "", "", "", "Some Stuff About Stuff", "GCCC", "OneTooMany");

        String newCardString = convertToJson(newCard);
        //Learned Behavior: No Nulls make this happy, find a way to allow nulls.
        //Can only Mark Fields that ARE to be reflected, which means if a Field is null, need to add logic to default value it likely.
        //Speak with Teammate, ORM is on the Horizon


    }

    private static void checkIfTableAble(Object object){
        if (Objects.isNull(object)){
            throw new RuntimeException("Object is Null");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonTableAnnotation.class)) {
            throw new RuntimeException("The class " + clazz.getSimpleName() + " is not annotated with a table!");
        }
    }

    private static void initializeObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private static String getJsonString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonField.class)) {
                jsonElementsMap.put(field.getName(), field.get(object).toString());
            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }


        public static String convertToJson(Object object) throws RuntimeException {
            try {
                checkIfTableAble(object);
                initializeObject(object);
                return getJsonString(object);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
