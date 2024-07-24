package com.malo.controllers;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping(value="/home", method=RequestMethod.GET)
    public String home() {
        return "Today is a good day!";
}

    @RequestMapping(value="/execute", method=RequestMethod.GET)

    public String execute() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstantiationBuilder instance = engine.getRuntimeService().createProcessInstanceByKey("Process_0ymoqy2");

        String item = "Computer";

        instance.setVariable("itemName", item);

        instance.businessKey("execute-endpoint");

        instance.executeWithVariablesInReturn();

        return "BPMN has executed";
}
}
