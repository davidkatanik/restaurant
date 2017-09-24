/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.csvDAOImpl;

import com.vsb.bl.domain.Employee;
import com.vsb.db.dao.EmployeeDAO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void addEmployee(Employee employee) {
    }

    @Override
    public void updateEmployee(Employee employee) {
    }

    @Override
    public void deleteEmployee(Employee employee) {
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee getEmployee(Long ide) {
        return null;
    }

    @Override
    public Employee getEmployee(String login, String password) {
        Employee result = null;
        try {
            result = new Employee();
            String csvFile = "D:\\zamestnanec.csv";
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ";";

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] rs = line.split(cvsSplitBy);
                if (login.equals(rs[3])) {
                    if (password.equals(rs[4])) {
                        result = new Employee();
                        result.setId(Long.parseLong(rs[0]));
                        result.setName(rs[1]);
                        result.setLastname(rs[2]);
                        result.setLogin(rs[3]);
                        result.setPassword(rs[4]);
                        result.setSalary(Long.parseLong(rs[5]));
                        result.setRc(rs[6]);
                        result.setEmail(rs[7]);
                        result.setTelephone(rs[8]);
                        result.setNote(rs[9]);
                        return result;
                    }
                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return result;
        }
    }
}
