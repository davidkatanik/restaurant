/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.dao;

import com.vsb.bl.domain.Employee;
import com.vsb.bl.domain.Order;
import java.util.List;

/**
 *
 * @author David
 */
public interface EmployeeDAO {

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployee(Long ide);
    
    Employee getEmployee(String login, String password);
}
