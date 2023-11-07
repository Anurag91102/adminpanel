package com.adminpanel.basic.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Id;

public class BrandDTO 
{
	private String bname;
	
	private MultipartFile image;

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	
}
