package com.adminpanel.basic.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.adminpanel.basic.model.Brand;
import com.adminpanel.basic.model.BrandDTO;
import com.adminpanel.basic.service.BrandService;

@Controller
public class BrandController 
{
	@Value("${brandDir}")
	private String uploadDirectory;
	
	@Autowired
	private BrandService brandService;
	
	@CachePut(value = "addBrand",key = "'addBrandKey'")
	@RequestMapping(path = "/addBrand", method = RequestMethod.POST)
	public String addBrand(@ModelAttribute BrandDTO brandDto) throws IllegalStateException, IOException, InterruptedException
	{
		MultipartFile image = brandDto.getImage();
		File uploadDir = new File(uploadDirectory);
		System.out.println(uploadDirectory.toString());
		if (!uploadDir.exists()) 
        {
           uploadDir.mkdirs();
        }
        String timestamp = String.valueOf(System.currentTimeMillis());
        String fileName = image.getOriginalFilename();
        String bname = brandDto.getBname();
        String modifiedBname = bname.replaceAll(" ", "_");
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = "brand_"+modifiedBname+"_"+timestamp+fileExtension;
        String filePath = uploadDirectory +"/"+ newFileName; 
        image.transferTo(new File(filePath));
        System.out.println(filePath);
        String imageUrl = "/image/brand/"+newFileName;
		Brand brand = new Brand(brandDto.getBname(),imageUrl);
		brandService.addBrand(brand);
		return "redirect:/brand";	
	}
	
	@GetMapping("/deletebrand/{id}")
	public String deleteBrand(@PathVariable("id") int id)
	{
		brandService.deletebyId(id);
		return "redirect:/brand";
	}
	
	@GetMapping("/updatebrand/{id}")
	public String updateBrand(@PathVariable("id") int id,org.springframework.ui.Model model)
	{
		Brand brand = brandService.getById(id);
		if(brand != null)
		{
			model.addAttribute("brandDetails", brand);
		}
		return "updatebrand";
	}
	
	@CachePut(value="brandUpdate",key="#id")
	@PostMapping("/updateBrand/{id}")
	public String updateBrandDetails(@PathVariable("id") int id,@ModelAttribute BrandDTO brandDto,RedirectAttributes attributes) throws IOException
	{
		Brand brand = brandService.getById(id);
		int updateBrandId = id;
		String newBname = brandDto.getBname();
		MultipartFile newImage = brandDto.getImage();
		Brand updatedBrand =brandService.updateBrand(updateBrandId, newBname, newImage);
		 if (updatedBrand != null) 
		 {
			 return "redirect:/brand";
	     } 
		 else 
		 {
			 attributes.addFlashAttribute("error", "No such brand Exist");
	         return "redirect:/brand";
	     }
		
	}
}

