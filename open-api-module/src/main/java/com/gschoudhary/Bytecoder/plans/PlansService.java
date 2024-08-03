package com.gschoudhary.Bytecoder.plans;

public interface PlansService {


    PlansDto create(PlansDto plansDto);

    PlansDto getAll(PlansDto plansDto);

    PlansDto getById(PlansDto plansDto);

    PlansDto update(PlansDto plansDto);

    PlansDto delete(PlansDto plansDto);
}
