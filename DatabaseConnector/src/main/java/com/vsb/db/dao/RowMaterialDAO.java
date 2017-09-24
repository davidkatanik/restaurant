/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsb.db.dao;

import com.vsb.bl.domain.Food;
import com.vsb.bl.domain.RowMaterial;
import java.util.List;

/**
 *
 * @author David
 */
public interface RowMaterialDAO {

    void addRowMaterial(RowMaterial rowMaterial);

    void updateRowMaterial(RowMaterial rowMaterial);

    void deleteRowMaterial(RowMaterial rowMaterial);

    List<RowMaterial> getAllRowMaterials();
    
    List<RowMaterial> getAllRowMaterials(Food food);

    RowMaterial getRowMaterial(Integer idrm);
}
