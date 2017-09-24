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
public class RowMaterial {

    private Long id;
    private String name;
    private String note;
    private Integer count;
    private List<Food> listOfFoods;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Food> getListOfFoods() {
        return listOfFoods;
    }

    public void setListOfFoods(List<Food> listOfFoods) {
        this.listOfFoods = listOfFoods;
    }
    
    
}
