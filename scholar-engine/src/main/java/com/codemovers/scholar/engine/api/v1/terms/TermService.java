/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.terms;

import com.codemovers.scholar.engine.api.v1.abstracts.AbstractService;
import com.codemovers.scholar.engine.api.v1.terms.entities.TermResponse;
import com.codemovers.scholar.engine.api.v1.terms.entities._Term;
import com.codemovers.scholar.engine.db.controllers.TermsJpaController;
import com.codemovers.scholar.engine.db.entities.SchoolData;
import java.util.logging.Logger;

/**
 *
 * @author mover 12/20/2017
 */
public class TermService extends AbstractService<_Term, TermResponse> {
    private static final Logger LOG = Logger.getLogger(TermService.class.getName());

    private final TermsJpaController controller;

    private static TermService service = null;

    public TermService() {
        controller = TermsJpaController.getInstance();
    }

    public static TermService getInstance() {
        if (service == null) {
            service = new TermService();
        }
        return service;
    }

    @Override
    public TermResponse create(SchoolData data, _Term entity) throws Exception {
        return super.create(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermResponse update(SchoolData data, _Term entity) throws Exception {
        return super.update(data, entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermResponse archive(SchoolData data, Integer id) throws Exception {
        return super.archive(data, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermResponse getById(SchoolData data, Integer Id) throws Exception {
        return super.getById(data, Id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TermResponse list(SchoolData data, Integer ofset, Integer limit) throws Exception {
        return super.list(data, ofset, limit); //To change body of generated methods, choose Tools | Templates.
    }


}
