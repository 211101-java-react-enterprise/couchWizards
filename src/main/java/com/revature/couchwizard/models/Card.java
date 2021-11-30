package com.revature.couchwizard.models;

/*  Logic for when we finish the ORM is commented out here

 */

import com.revature.couchwizard.annotations.Column;
import com.revature.couchwizard.annotations.Id;
import com.revature.couchwizard.annotations.Table;

import java.util.Arrays;

@Table(tableName="cards")
public class Card {

    @Column(columnName="card_name", isStatic = true)
    private String name;
    @Column(columnName="value")
    private Double value;
    @Column(columnName="supertype")
    private String superTypes;
    @Column(columnName="subtype")
    private String subTypes;
    @Column(columnName="c_power")
    private String power;
    @Column(columnName="c_tough")
    private String toughness;
    @Column(columnName="c_desc")
    private String description;
    @Column(columnName="c_cost")
    private String color;
    @Column(columnName="print_set", isStatic = true)
    private String printSet;
    @Id(columnName="card_id")
    private String id;



    public Card() {    }

    public Card(String name, Double value, String superTypes, String subTypes, String power, String toughness, String description, String color, String printSet) {
        this.name = name;
        this.value = value;
        this.superTypes = superTypes;
        this.subTypes = subTypes;
        this.power = power;
        this.toughness = toughness;
        this.description = description;
        setColor(color);
        this.printSet = printSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
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
        char sortArray[] = color.toUpperCase().toCharArray();
        Arrays.sort(sortArray);
        this.color = new String(sortArray);
    }

    public String getPrintSet() {
        return printSet;
    }

    public void setPrintSet(String printSet) {
        this.printSet = printSet;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    // To string method for debugging purposes
    @Override
    public String toString() {
        return "Card [id= " + id + ", name=" + name + ", value=" + value + ", superTypes=" + superTypes +
                ", subTypes=" + subTypes + ", power=" + power + ", toughness=" + toughness + ", description=" +
                description + ", color cost=" + color + ", print set=" + printSet + "]";
    }

}
