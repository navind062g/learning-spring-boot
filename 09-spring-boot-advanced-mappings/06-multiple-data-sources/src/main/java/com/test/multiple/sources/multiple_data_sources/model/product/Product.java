package com.test.multiple.sources.multiple_data_sources.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "products")
public class Product {
	
	@Id
	private int id;
	
	private String name;
	
	private double price;
	
	public Product() {}
	
	public Product(String theName, double thePrice) {
		this.name = theName;
		this.price = thePrice;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
