package com.revature.couchwizard.models;

public class Card {

    private String name;
    private double value;
    private String superTypes;
    private String subTypes;
    private String power;
    private String toughness;
    private String description;
    private String color;
    private String printSet;
    private String id;



    public Card() {    }

    public Card(String name, double value, String superTypes, String subTypes, String power, String toughness, String description, String color, String printSet) {
        this.name = name;
        this.value = value;
        this.superTypes = superTypes;
        this.subTypes = subTypes;
        this.power = power;
        this.toughness = toughness;
        this.description = description;
        this.color = color;
        this.printSet = printSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSuperTypes() {
        return superTypes;
    }

    public void setSuperTypes(String superTypes) {
        this.superTypes = superTypes;
    }

    public String getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(String subTypes) {
        this.subTypes = subTypes;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrintSet() {
        return printSet;
    }

    public void setPrintSet(String printSet) {
        this.printSet = printSet;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

}
