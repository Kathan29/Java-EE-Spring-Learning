package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeDeserializeDemo {
	
	private static class Serialization implements Serializable{
		private transient int id;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	private static void doSerialization() {
		Serialization serialObj = new Serialization();
		serialObj.setName("Java");
		serialObj.setId(4);
		
		System.out.println("Before Serialization");
		System.out.println("Name :"+serialObj.getName());
		System.out.println("Id : "+serialObj.getId());
		
		try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("serialization.ser")))){
			out.writeObject(serialObj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Serialization Done");
		
	}
	
	private static void doDeserialization() {
		
		try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("serialization.ser")))){
			Serialization serObj = (Serialization)in.readObject();
			
			System.out.println("After Deserialization");
			System.out.println("Name :"+serObj.getName());
			System.out.println("Id : "+serObj.getId()); //Because it is transient it will get default value as 0 (integer)
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Deserialization Done");
		
	}
	public static void main(String[] args) {
		SerializeDeserializeDemo.doSerialization();
		SerializeDeserializeDemo.doDeserialization();
	}

}
