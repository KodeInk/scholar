/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.db.dto;

import com.codemovers.scholar.engine.db.EngineJpaController;
import com.codemovers.scholar.engine.db.controllers.RolesJpaController;
import com.codemovers.scholar.engine.db.entities.Roles;
import java.util.logging.Logger;

/**
 *
 * @author mover
 */
public class RolesJpaDTO  extends EngineJpaController{
    
     protected static final Logger LOG = Logger.getLogger(RolesJpaDTO.class.getName());

    private static RolesJpaDTO dto = null;
    
     public static RolesJpaDTO getInstance() {
        if (dto == null) {
            dto = new RolesJpaDTO();
        }
        return dto;
    }

      public RolesJpaDTO() {
        super(Roles.class);
    }
     

}
