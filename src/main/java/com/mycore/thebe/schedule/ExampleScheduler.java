package com.mycore.thebe.schedule;

import org.springframework.stereotype.Component;

/**
 * Example class to execute schedule method
 * @author Thebe.Alfarisi
 * @since June, 29th 2018
 * @version 1.0
 * 
 */
//@Component("example")
@Component("test")
public class ExampleScheduler {

	public void printMessage() {
        System.out.println("I am called by Spring scheduler");
    }
	
}
