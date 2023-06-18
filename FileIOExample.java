package com.lgcns.tct.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lgcns.tct.fileio.model.Proxy;

public class FileIOExample {
	
	public static void main(String args[]) throws IOException {
		List<String> commands = FileIOExample.readTextFile("examples/Proxy-1.txt");
		FileIOExample.writeTextFile("examples/out/Proxy-1.txt", commands);
		
		Proxy proxy = FileIOExample.readJsonFile("examples/Proxy-1.json");
		FileIOExample.writeJsonFile("examples/out/Proxy-1.json", proxy);
		
		String command = readSystemIn();
		System.out.println(command);
	}
	
	public static String readSystemIn() throws IOException {
		String command = "";
		try(BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in))) {
		
			command = reader.readLine();
			
		} catch(IOException e) {
			throw e;
		}
		return command;
	}
	
	public static Proxy readJsonFile(String path) throws IOException {	
		Gson gson = new Gson();
		Proxy proxy = null;
		
		try (Reader jsonReader = new FileReader(path);){
			
			proxy = gson.fromJson(jsonReader, Proxy.class);
			proxy.toMap();
			
			System.out.println(proxy);
			
		} catch(IOException e ) {
			System.out.println(e.getMessage());
		} 
	
		return proxy;
	}
	
	public static void writeJsonFile(String path, Proxy proxy) throws IOException {
		Gson gson = new Gson();
		try (FileWriter writer = new FileWriter(path)) {
			gson.toJson(proxy, writer);
			writer.flush();
		} catch(IOException e) {
			throw e;
		} 
	}
	
	
	public static List<String> readTextFile(String path) throws IOException {
		List<String> commands = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String str;
			while((str = reader.readLine()) != null) {
				commands.add(str);
			}
			System.out.println(commands);
		} catch(IOException e) {
			throw e;
		} 
		
		return commands;
	}
	
	public static void writeTextFile(String path, List<String> contents) throws IOException {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			for(String content: contents) {
				writer.write(content + "\n");
			}
		} catch(IOException e) {
			throw e;
		} 
	}

}
