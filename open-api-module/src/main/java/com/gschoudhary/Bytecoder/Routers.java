package com.gschoudhary.Bytecoder;

import com.gschoudhary.Bytecoder.exams.ExamDtos;
import com.gschoudhary.Bytecoder.exams.ExamsServiceImpl;
import com.gschoudhary.open2api.restcontroller.router.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Routers {


    @Autowired
    private Routers(RolesService rolesService, ExamsServiceImpl examsService) {
        Router.POST("/api/v1/codea", (c) -> rolesService.addRole());
        Router.POST("/api/v1/codeb", (c) -> rolesService.getRoles());
    }

}
