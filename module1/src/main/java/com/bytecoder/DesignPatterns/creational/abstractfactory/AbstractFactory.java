package com.bytecoder.DesignPatterns.creational.abstractfactory;

import com.bytecoder.DesignPatterns.creational.abstractfactory.textbox.TextBox;
import com.bytecoder.DesignPatterns.creational.abstractfactory.button.Button;

public interface AbstractFactory {
     Button getButton();

     TextBox getTextBox();
}
