/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author David
 */
public class Order {

    private Long id;
    private Integer table;
    private Date ordered;
    private Date completed;
    private String Note;
    private Employee employee;
    private Ticket ticket;
    private List<Food> listOfFoods;
    private List<Drink> listOfDrinks;

    public Order() {
        listOfFoods = new LinkedList<>();
    }

    public Order(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<Food> getListOfFoods() {
        return listOfFoods;
    }

    public void setListOfFoods(List<Food> listOfFoods) {
        this.listOfFoods = listOfFoods;
    }

    public List<Drink> getListOfDrinks() {
        return listOfDrinks;
    }

    public void setListOfDrinks(List<Drink> listOfDrinks) {
        this.listOfDrinks = listOfDrinks;
    }

}
