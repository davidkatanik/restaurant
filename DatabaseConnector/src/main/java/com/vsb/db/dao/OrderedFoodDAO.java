/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.dao;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import com.vsb.bl.domain.OrderedFood;
import java.util.List;

/**
 *
 * @author David
 */
public interface OrderedFoodDAO {

    void addOrderedFoodToOrder(OrderedFood orderedFood);

    void updateOrderedFoodInOrder(OrderedFood orderedFood);

    void deleteOrderedFoodFromOrder(OrderedFood orderedFood);

    List<OrderedFood> getAllOrderedFoods();

    OrderedFood getOrderedFood(Order order, Food food);

    List<OrderedFood> getAllOrderedFoods(Order order);
    
    List<OrderedFood> getAllOrderedFoods(Employee employee);

}
