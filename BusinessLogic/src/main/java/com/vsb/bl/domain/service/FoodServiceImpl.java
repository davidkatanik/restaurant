/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.bl.domain.service;

import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.Order;
import com.vsb.db.dao.FoodDAO;
import com.vsb.db.postgresDAOImpl.FoodDAOImpl;
import java.util.List;

/**
 *
 * @author David
 */
public class FoodServiceImpl implements FoodService{

    FoodDAO foodDAO = new FoodDAOImpl();
    
    @Override
    public List<Food> getAllFoods() {
        return foodDAO.getAllFoods();
    }

    @Override
    public List<Food> getAllFoods(Order order) {
        return foodDAO.getAllFoods(order);
    }
    
}
