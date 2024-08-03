package com.gschoudhary.Bytecoder.papers;

public interface PaperService {


    PaperDto create(PaperDto paperDto);

    PaperDto getAll(PaperDto paperDto);

    PaperDto getById(PaperDto paperDto);

    PaperDto update(PaperDto paperDto);

    PaperDto delete(PaperDto paperDto);
}
