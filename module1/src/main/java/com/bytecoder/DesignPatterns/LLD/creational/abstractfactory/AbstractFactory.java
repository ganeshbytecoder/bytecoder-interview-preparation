package com.bytecoder.DesignPatterns.LLD.creational.abstractfactory;

import com.bytecoder.DesignPatterns.LLD.creational.abstractfactory.textbox.TextBox;
import com.bytecoder.DesignPatterns.LLD.creational.abstractfactory.button.Button;

public interface AbstractFactory {
     Button getButton();

     TextBox getTextBox();
}
