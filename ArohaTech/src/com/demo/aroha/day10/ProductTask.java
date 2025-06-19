package com.demo.aroha.day10;

public class ProductTask {
	private int pId;
	private String name;
	private double price;
	public ProductTask(int pId, String name, double price) {
		super();
		this.pId = pId;
		this.name = name;
		this.price = price;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductTask [pId=" + pId + ", name=" + name + ", price=" + price + "]";
	}
	

}
