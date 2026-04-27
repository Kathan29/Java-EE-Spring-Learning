package iocDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import iocDemo.controllers.ControllerClass;
import iocDemo.services.Service;
import iocDemo.services.ServiceImpl;

public class Launcher {

	public static void main(String[] args) {
		
			System.out.println("Creating ioc container");
			ApplicationContext context = new AnnotationConfigApplicationContext(ConfigClass.class);
			
			//All bean class created are singleton class by default
			Service ser1 = context.getBean(ServiceImpl.class);
			Service ser2 = context.getBean(ServiceImpl.class);
			if(ser1==ser2) {
				System.out.println("Singleton : "+ser1);
			}
			
			//To demonstrate that using fields with autowire, it is injecting or not?
			/*
			ControllerClass controller = (ControllerClass)context.getBean("controllerClass"); //One more way instead of ControllerClass.class
			System.out.println(controller.getService());
			System.out.println(controller.getMyRepository());
			*/
			
			
			
			
	}

}
