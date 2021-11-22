package com.revature.couchwizard.models;

public class card {

    private String name;
    private String cost;
    private String superTypes;
    private String subTypes;
    private String power;
    private String toughness;
    private String description;
    private String[] color;
    private String printSet;



    public card() {    }

    public card(String name, String cost, String superTypes, String subTypes, String power, String toughness, String description, String[] color, String printSet) {
        this.name = name;
        this.cost = cost;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public String getPrintSet() {
        return printSet;
    }

    public void setPrintSet(String printSet) {
        this.printSet = printSet;
    }

    public void colorFromCost(String cost){
        int i = 0;

        if (cost.contains("B")){
            this.color[i] += "Black";
            i++;
        }
        if (cost.contains("W")){
            this.color[i] += "White";
            i++;
        }
        if (cost.contains("G")){
            this.color[i] += "Green";
            i++;
        }
        if (cost.contains("U")){
            this.color[i] += "Blue";
            i++;
        }
        if (cost.contains("R")){
            this.color[i] += "Red";
            i++;
        }

        i = 0;
        //Think about Hybrid mana. "/" maybe or Keyword
    }


}
