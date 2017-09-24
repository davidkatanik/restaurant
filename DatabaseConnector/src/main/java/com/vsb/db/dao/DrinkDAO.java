/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.dao;

import com.vsb.bl.domain.Drink;
import com.vsb.bl.domain.Order;
import java.util.List;

/**
 *
 * @author David
 */
public interface DrinkDAO {

    void addDrink(Drink drink);

    void updateDrink(Drink drink);

    void deleteDrink(Drink drink);

    List<Drink> getAllDrinks();

    Drink getDrink(int idd);
    
    List<Drink> getAllDrinks(Order order);
}
