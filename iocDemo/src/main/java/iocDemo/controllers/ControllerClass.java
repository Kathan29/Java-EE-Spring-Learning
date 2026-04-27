package iocDemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import iocDemo.dao.MyRepository;

import iocDemo.services.Service;

@Component
public class ControllerClass {
	
	//DI using setters
	//To demonstrate that using setter : one thing is service and repo cannot be final :
	/*
	private Service service;
	private MyRepository repo;
	
	@Autowired
	public void setMyRepository(MyRepository repo) {
		this.repo = repo;
	}
	
	@Autowired
	public void setService(Service service) {
		this.service = service;
	}
	public MyRepository getMyRepository() {
		return repo;
	}
	public Service getService() {
		return service;
	}
	*/
	
	//DI using fields : 
	/*
	@Autowired
	private final Service service= null;
	@Autowired
	private final MyRepository repo = null;
	
	//To demonstrate that using fields with autowire, it is injecting or not?
	public MyRepository getMyRepository() {
		return repo;
	}
	public Service getService() {
		return service;
	}
	*/
	
	 
	
	
	  private final Service service;
	  private final MyRepository repo;
	  
	  //If more than one constructor than keeping it service and repo final will create problem 
	  /// eg: public ControllerClass(){} then here we need to this.service = null; inside this constructor.
	  /// simple solution would be remove final if needed.
	  
	@Autowired
	public ControllerClass(Service service,MyRepository repo) {
		this.service = service;
		System.out.println("Service injected : "+service);
		this.repo = repo;
		System.out.println("Repo injected : "+repo);
	}
	
	
	
}
