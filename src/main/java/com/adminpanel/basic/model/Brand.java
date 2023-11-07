package com.adminpanel.basic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String bname;
	
	private String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brand(int id, String bname, String image) {
		super();
		this.id = id;
		this.bname = bname;
		this.image = image;
	}

	public Brand(String bname, String image) {
		super();
		this.bname = bname;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", bname=" + bname + ", image=" + image + "]";
	}
}
