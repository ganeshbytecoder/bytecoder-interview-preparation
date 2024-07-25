package com.bytecoder.DesignPatterns.structural.pipe_filters;



public class App {

    public static void main(String[] args) {
        PipelineExecutorManager pipelineExecutorManager = new PipelineExecutorManager();
        System.out.println(pipelineExecutorManager.process("1 2 3 .56"));
        System.out.println(pipelineExecutorManager.process2("1 2 3 .56"));
        System.out.println(pipelineExecutorManager.process3("/home/ganesh/Desktop/Kaleidofin/Own Projects/Design-patterns-examples/src/main/resources/user.txt"));

//        Function 0> then -> then -> then
//        tasks -> get request -> dto2entity -> parsist -> sms -> save-> push notification ->
    }

}
