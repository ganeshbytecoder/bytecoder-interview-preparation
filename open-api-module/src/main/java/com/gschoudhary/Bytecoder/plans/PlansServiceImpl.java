package com.gschoudhary.Bytecoder.plans;

import org.springframework.stereotype.Service;

@Service
public class PlansServiceImpl implements PlansService {


    @Override
    public PlansDto create(PlansDto plansDto) {
        System.out.println(plansDto);
        plansDto.setExamId(122);
        return plansDto;
    }

    @Override
    public PlansDto getAll(PlansDto plansDto) {
        System.out.println(plansDto);
        plansDto.setExamId(122);
        return plansDto;
    }


    @Override
    public PlansDto getById(PlansDto plansDto) {
        System.out.println(plansDto);
        plansDto.setExamId(122);
        return plansDto;
    }


    @Override
    public PlansDto update(PlansDto plansDto) {
        System.out.println(plansDto);
        plansDto.setExamId(122);
        return plansDto;
    }


    @Override
    public PlansDto delete(PlansDto plansDto) {
        System.out.println(plansDto);
        plansDto.setExamId(122);
        return plansDto;
    }
}
