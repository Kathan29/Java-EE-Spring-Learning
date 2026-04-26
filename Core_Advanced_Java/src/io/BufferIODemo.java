package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferIODemo {
	static String inFileStr = "brokenPackage.png";
	static String outFileStr = "brokenPackage-out.png";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		fileIOWithoutBuffer();
		fileIOWithBuffer();
	}

	private static void fileIOWithoutBuffer() throws FileNotFoundException, IOException {
		System.out.println("Inside FileIO without buffer");
		
		File inFil = new File(inFileStr);
		System.out.println("File length: "+inFil.length());
		
		long startTime,elapsedTime;
		startTime = System.nanoTime();
		
		try(FileInputStream in = new FileInputStream(inFileStr);
				FileOutputStream out = new FileOutputStream(outFileStr)){
			int byteRead;
			
			while((byteRead=in.read())!=-1) {
				out.write(byteRead);
			}
		}
		
		elapsedTime = System.nanoTime()-startTime;
		System.out.println("Total time elapsed (in msec) : "+ (elapsedTime/1000000.0) + " without buffer\n");
	}

	private static void fileIOWithBuffer() throws FileNotFoundException, IOException {
		System.out.println("Inside FileIO with Buffer");
		
		File inFil = new File(inFileStr);
		System.out.println("File length: "+inFil.length());
		
		long startTime,elapsedTime;
		startTime = System.nanoTime();
		
		try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))){
			
			byte[] buff = new byte[2000];
			int numBytesRead;
			while((numBytesRead = in.read(buff))!=-1) {
				out.write(buff, 0, numBytesRead);
			}
		}
		
		elapsedTime = System.nanoTime()-startTime;
		System.out.println("Total time elapsed (in msec) : "+ (elapsedTime/1000000.0) + " with buffer\n");
				
		
	}
}
