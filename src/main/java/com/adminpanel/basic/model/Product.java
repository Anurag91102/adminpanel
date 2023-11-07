package com.adminpanel.basic.model;

import java.util.List; 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String pname;
	
	private String description;
	
	private String price;
	
	private String image;

	private List<String> images;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", pname=" + pname + ", description=" + description + ", price=" + price
				+ ", image=" + image + ", images=" + images + "]";
	}

	public Product() 
	{
		super();
	}

	public Product(String pname, String description, String price, String image, List<String> images) {
		super();
		this.pname = pname;
		this.description = description;
		this.price = price;
		this.image = image;
		this.images = images;
	}

	public Product(int id, String pname, String description, String price, String image, List<String> images) {
		super();
		this.id = id;
		this.pname = pname;
		this.description = description;
		this.price = price;
		this.image = image;
		this.images = images;
	}	
}
