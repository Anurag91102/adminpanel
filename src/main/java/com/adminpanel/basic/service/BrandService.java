package com.adminpanel.basic.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.adminpanel.basic.model.Brand;
import com.adminpanel.basic.repo.BrandRepo;

@Service
public class BrandService 
{
	@Autowired
	private BrandRepo brandRepo;
	
	private final String uploadDirectory = "C:/Users/User/Downloads/basic/src/main/resources/static";
	
	public Brand addBrand(Brand brand)
	{
		return brandRepo.save(brand);
	}
	
	@Cacheable(value = "Brand",key = "#id")
	public Brand getById(int id)
	{
		System.out.println("From db");
		return brandRepo.findById(id).orElse(null);
	}
	
	public List<Brand> getAll()
	{
		System.out.println("from db list of brand");
		return brandRepo.findAll();
	}
	
	public void deletebyId(int id)
	{
		Brand brand = brandRepo.findById(id).orElse(null);
		if(brand != null)
		{
			String imageUrl = brand.getImage();
			 if (imageUrl != null) 
			 {
				 String filePath = uploadDirectory + imageUrl;
				 File imageFile = new File(filePath);
				 if (imageFile.exists()) 
				 {
	                if (imageFile.delete()) 
	                {
	                    System.out.println("Image file deleted successfully. "+brand.getBname());
	                } 
	                else 
	                {
	                    System.out.println("Failed to delete image file. "+brand.getBname());
	                }
		         }
			 }
			 brandRepo.deleteById(id);
		}	
	}
	
	 public Brand updateBrand(int id, String newBname, MultipartFile newImage) throws IOException 
	 {
		 Brand existingBrand = brandRepo.findById(id).orElse(null);
	     if (existingBrand != null) 
	     {
	    	 if(newBname != null)
	    	 {	
		        existingBrand.setBname(newBname);
	    	 }
	    	if(newImage != null && !newImage.isEmpty())
	    	{
		        String oldImageUrl = existingBrand.getImage();
		        if (oldImageUrl != null) 
		        {
		            String oldFilePath = uploadDirectory + oldImageUrl;
		            File oldImageFile = new File(oldFilePath);
		
		            if (oldImageFile.exists() && oldImageFile.delete()) {
		                System.out.println("Old image file deleted successfully. "+existingBrand.getBname());
		            }
		        }

	            String timestamp = String.valueOf(System.currentTimeMillis());
	            String fileName = newImage.getOriginalFilename();
	            String modifiedBname = newBname.replaceAll(" ", "_");
	            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
	            String newFileName = "brand_" + modifiedBname + "_" + timestamp + fileExtension;
	
	            String newFilePath = uploadDirectory + "/image/brand/" + newFileName;
	            newImage.transferTo(new File(newFilePath));
	            System.out.println("New image file saved: " + newFilePath);
	            existingBrand.setImage("/image/brand/" + newFileName);
	        }
	        
	        return brandRepo.save(existingBrand);
	     }
	     else
	     {
	    	 return null;
	     }
	 }
}
