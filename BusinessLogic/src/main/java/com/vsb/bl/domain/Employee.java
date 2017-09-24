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
public class Employee {
    private Long id;
    private String name;
    private String lastname;
    private String login;
    private String password;
    private Long salary;
    private String rc;
    private String email;
    private String telephone;
    private String note;
    private Role role;
    private List<Food> listOfFoods;
    private List<Drink> listOfDrinks;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
