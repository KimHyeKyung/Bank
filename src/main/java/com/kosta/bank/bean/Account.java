package com.kosta.bank.bean;

public class Account {
	private String id;
	private String name;
	private int balance;
	private String sect;
	private String grade;
	
	public Account() {
		super();
	}
	public Account(String id, String name, int balance, String sect, String grade) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.sect = sect;
		this.grade = grade;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getSect() {
		return sect;
	}
	public void setSect(String sect) {
		this.sect = sect;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public void deposit(int money) {
		this.balance += money;
	}
	
	public void withdraw(int money) {
		this.balance -= money;
	}
}
