package com.malo.controllers;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Simple endpoint that returns a static string
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "Today is a good day!";
    }

    // Endpoint to execute a BPMN process
    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    public String execute() {
        // Get the default process engine
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        // Define the process key, item, and business key
        String processKey = "Process_0ymoqy2";
        String item = "Computer";
        String businessKey = "execute-endpoint";

        try {
            // Create and start a new process instance
            ProcessInstance processInstance = engine.getRuntimeService()
                .createProcessInstanceByKey(processKey)  // Specify the process definition key
                .setVariable("itemName", item)           // Set a process variable
                .businessKey(businessKey)                // Set a business key
                .execute();                              // Start the process instance

            // Log the item and business key for debugging
            //System.out.println(item);
            //System.out.println("Business Key: " + businessKey);

            // Return the process instance ID
            return processInstance.getProcessInstanceId();
        } catch (Exception e) {
            // Print the stack trace and return an error message in case of an exception
            e.printStackTrace();
            return "Error executing BPMN: " + e.getMessage();
        }
    }
}
