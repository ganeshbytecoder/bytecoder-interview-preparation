package com.bytecoder.DesignPatterns.creational.abstractfactory.button;

public class ButtonFactory {
    public Button getButton(String buttonType) {
        Button button = null;
        if(buttonType.equals("Android")){
            button= new AndroidButton();
        }
        if(buttonType.equals("IOS")){
            button = new IOSButton();
        }

        return button;
    }
}
