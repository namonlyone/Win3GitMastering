package com.aavn.demo.model;

/**
 * 
 * @author lqdung
 *
 */
public class Result {

	String name;
	String born;

	public Result(String name, String born) {
		this.name = name;
		this.born = born;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getBorn() {
		return born;
	}
	public void setBorn(String born) {
		this.born = born;
	}
}