package iocDemo.services;

import org.springframework.stereotype.Component;

@Component
public class ServiceImpl implements Service{
	
	public ServiceImpl() {
		System.out.println("Service Impl created..");
	}
}
