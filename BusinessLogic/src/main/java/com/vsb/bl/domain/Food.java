/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain;

import java.util.List;

/**
 *
 * @author David
 */
public class Food {

    private Long id;
    private String name;
    private Integer weigth;
    private Unit unit;
    private Long cost;
    private String note;
    private List<Employee> listOfEmployees;
    private List<RowMaterial> listOfMaterials;

    public Food(Long id) {
        this.id=id;
    }

    public Food() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }

    public void setListOfEmployees(List<Employee> listOfEmployees) {
        this.listOfEmployees = listOfEmployees;
    }

    public List<RowMaterial> getListOfMaterials() {
        return listOfMaterials;
    }

    public void setListOfMaterials(List<RowMaterial> listOfMaterials) {
        this.listOfMaterials = listOfMaterials;
    }

}
