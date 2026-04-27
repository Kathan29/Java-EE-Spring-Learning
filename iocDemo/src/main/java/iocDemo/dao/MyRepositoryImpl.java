package iocDemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MyRepositoryImpl implements MyRepository {

	public MyRepositoryImpl() {
		System.out.println("My repository impl created...");
	}
}
