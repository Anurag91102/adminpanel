package com.adminpanel.basic.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminpanel.basic.model.Brand;
import com.adminpanel.basic.model.Product;
import com.adminpanel.basic.repo.ProductRepo;

@Service
public class ProductService 
{
	private final String uploadDirectory = "C:/Users/User/Downloads/basic/src/main/resources/static";
	
	@Autowired
	public ProductRepo productRepo;
	
	public Product addProduct(Product product)
	{
		return productRepo.save(product);
 	}
	
	public List<Product> getAll()
	{
		return productRepo.findAll();
	}
	
	public Product getById(int id)
	{
		return productRepo.findById(id).orElse(null);
	}
	
	public void deletebyId(int id)
	{
		Product product = productRepo.findById(id).orElse(null);
		if(product != null)
		{
			String imageUrl = product.getImage();
			 if (imageUrl != null) 
			 {
				 String filePath = uploadDirectory + imageUrl;
				 File imageFile = new File(filePath);
				 if (imageFile.exists()) 
				 {
		                if (imageFile.delete()) 
		                {
		                    System.out.println("Image file deleted successfully. "+product.getPname());
		                } 
		                else 
		                {
		                    System.out.println("Failed to delete image file. "+product.getPname());
		                }
		         }
			 }
			 
			 List<String> multiImageUrl = product.getImages();
			 for(String image : multiImageUrl)
			 {
				 if (image != null) 
				 {
					 String filePath = uploadDirectory + image;
					 File imageFile = new File(filePath);
					 if (imageFile.exists()) 
					 {
		                if (imageFile.delete()) 
		                {
		                    System.out.println("List Image file deleted successfully. "+product.getPname());
		                } 
		                else 
		                {
		                    System.out.println("List Failed to delete image file. "+product.getPname());
		                }
			         }
				 }
			 }
			 productRepo.deleteById(id);
		}
	}
}
