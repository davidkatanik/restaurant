/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.dao;

import com.vsb.bl.domain.Reclamation;
import java.util.List;

/**
 *
 * @author David
 */
public interface ReclamationDAO {

    void addReclamation(Reclamation reclamation);

    void updateReclamation(Reclamation reclamation);

    void deleteReclamation(Reclamation reclamation);

    List<Reclamation> getAllReclamations();

    Reclamation getReclamation(Integer idr);
}
