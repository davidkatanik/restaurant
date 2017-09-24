/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.postgresDAOImpl;

import com.vsb.bl.domain.Reclamation;
import com.vsb.db.dao.ReclamationDAO;
import java.util.List;

/**
 *
 * @author David
 */
public class ReclamationDAOImpl implements ReclamationDAO {

    private static final String TABLE_NAME = "reklamace";

    @Override
    public void addReclamation(Reclamation reclamation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateReclamation(Reclamation reclamation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteReclamation(Reclamation reclamation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reclamation getReclamation(Integer idr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
