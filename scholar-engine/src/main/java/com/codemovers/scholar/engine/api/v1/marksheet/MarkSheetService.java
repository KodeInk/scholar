/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.marksheet;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.marksheet.entities.MarkSheetResponse;
import com.codemovers.scholar.engine.api.v1.marksheet.entities._MarkSheet;
import com.codemovers.scholar.engine.db.controllers.MarksheetJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class MarkSheetService extends AbstractService<_MarkSheet, MarkSheetResponse> {

    private static final Logger LOG = Logger.getLogger(MarkSheetService.class.getName());

    private final MarksheetJpaController controller;

    private static MarkSheetService service = null;

    public MarkSheetService() {
        controller = MarksheetJpaController.getInstance();

    }

    public static MarkSheetService getInstance() {
        if (service == null) {
            service = new MarkSheetService();
        }
        return service;
    }

    @Override
    public MarkSheetResponse create(SchoolData data, _MarkSheet entity) throws Exception {
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarkSheetResponse update(SchoolData data, _MarkSheet entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarkSheetResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarkSheetResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MarkSheetResponse list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }

}
