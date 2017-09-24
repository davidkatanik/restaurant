/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.dao;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import java.util.List;

/**
 *
 * @author David
 */
public interface FoodDAO {

    void addFood(Food food);

    void updateFood(Food food);

    void deleteFood(Food food);

    List<Food> getAllFoods();

    Food getFood(Integer idf);
    
    List<Food> getAllFoods(Order order);
    
    List<Food> getAllFoods(Employee employee);

}
