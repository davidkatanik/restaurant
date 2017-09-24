/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain.service;

import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import java.util.List;

/**
 *
 * @author David
 */
public interface FoodService {
    List<Food> getAllFoods();
    List<Food> getAllFoods(Order order);
}
