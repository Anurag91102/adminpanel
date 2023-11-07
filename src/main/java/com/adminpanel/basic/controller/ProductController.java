package com.adminpanel.basic.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import com.adminpanel.basic.model.Brand;
import com.adminpanel.basic.model.Product;
import com.adminpanel.basic.model.ProductDTO;
import com.adminpanel.basic.payloads.Response;
import com.adminpanel.basic.payloads.TypeData;
import com.adminpanel.basic.service.BrandService;
import com.adminpanel.basic.service.ProductService;

@Controller
public class ProductController 
{
	@Value("${uploadDir}")
	private String uploadDirectory;
	
	private final String imageDirectory = "C:/Users/User/Downloads/basic/src/main/resources/static";
	
	@Value("${devurl}")
	private String devUrl;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	public ProductService productService;
	
	@PostMapping("/collectionApi")
	public ResponseEntity<?> typeHandle()
	{
		List<Product> products = productService.getAll();
        List<Brand> brands = brandService.getAll();
       
        for (Brand brand : brands) 
        {
            brand.setImage(devUrl+brand.getImage());
        }
        
        for(Product product :products)
        {
        	product.setImage(devUrl+product.getImage());
        	List<String> images = product.getImages();
        	System.out.println(images);
        	if (images != null) 
        	{
                List<String> updatedImages = new ArrayList<>();
                for (String image : images) 
                {
                    updatedImages.add(devUrl + image); 
                }
                product.setImages(updatedImages);
            }
        }
        List<Object> combinedData = Stream.of(
                new TypeData<>("product", products),
                new TypeData<>("brand", brands)
            ).collect(Collectors.toList());
        
        Response response = new Response("1","Data Fetched Successfully",combinedData);
        return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/addProduct")
	public RedirectView addProduct(@ModelAttribute ProductDTO productDto) throws IllegalStateException, IOException 
	{
		MultipartFile image = productDto.getImage();
		List<MultipartFile> images = productDto.getImages();
		File uploadDir = new File(uploadDirectory);
		if (!uploadDir.exists()) 
        {
           uploadDir.mkdirs();
        }
        String timestamp = String.valueOf(System.currentTimeMillis());
        
        //for single image
        String fileName = image.getOriginalFilename();
    	String imageName= productDto.getPname();
    	String modifiedPname = imageName.replaceAll(" ", "_");
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = "product_"+modifiedPname+"_"+timestamp+fileExtension;
        String filePath = uploadDirectory + File.separator + newFileName;
        image.transferTo(new File(filePath));	
        
        //for multi images
    	int i = 1;
        List<String> productImages = new ArrayList<String>();
        for(MultipartFile file: images)
        {
        	String multiFileName = file.getOriginalFilename();
        	
        	System.out.println(multiFileName);
        	String mutliImageName= productDto.getPname();
        	String multimodifiedPname = mutliImageName.replaceAll(" ", "_");
        	String multiFileExtension = multiFileName.substring(multiFileName.lastIndexOf("."));
            String newMultiFileName = "product_"+multimodifiedPname+"_"+i+"_"+timestamp+multiFileExtension;
            String mutliFilePath = uploadDirectory + File.separator + newMultiFileName;
            try 
            {
               file.transferTo(new File(mutliFilePath));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            productImages.add("/image/"+newMultiFileName);
            i++;
        }
        
        Product product = new Product(productDto.getPname(),productDto.getDescription(),productDto.getPrice(),"/image/"+newFileName,productImages);
    	productService.addProduct(product);
    	RedirectView redirectView = new RedirectView(); 
		redirectView.setUrl("/product"); 
		return redirectView; 
	}
	
	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable("id") int id)
	{
		productService.deletebyId(id);
		return "redirect:/product";
	}
	
	@GetMapping("/updateproduct/{id}")
	public String updateProductView(@PathVariable("id") int id,Model model)
	{
		Product product  = productService.getById(id);
		model.addAttribute("productDetails",product);
		return "updateproduct";
	}
	
	@PostMapping("/updateProduct/{id}")
	public String updateProductDetails(@PathVariable("id") int id,@ModelAttribute ProductDTO productDto) throws IllegalStateException, IOException
	{
		Product existingProduct = productService.getById(id);
		MultipartFile newImage = productDto.getImage();
	    List<MultipartFile> newImages = productDto.getImages();
	    
	    if(existingProduct !=null)
	    {
	    	if(productDto.getPname()!=null)
    		{
	    		existingProduct.setPname(productDto.getPname());
    		}
    		if(productDto.getDescription()!=null)
    		{
    			existingProduct.setDescription(productDto.getDescription());
    		}
    		if(productDto.getPrice()!=null)
    		{
    			existingProduct.setPrice(productDto.getPrice());
    		}
	    	if(newImage != null && !newImage.isEmpty())
	    	{
		        String oldImageUrl = existingProduct.getImage();
		        if (oldImageUrl != null) 
		        {
		            String oldFilePath = imageDirectory + oldImageUrl;
		            File oldImageFile = new File(oldFilePath);
		
		            if (oldImageFile.exists() && oldImageFile.delete()) 
		            {
		                System.out.println("Old image file deleted successfully. "+existingProduct.getPname());
		            }
		        }

	            String timestamp = String.valueOf(System.currentTimeMillis());
	            String fileName = newImage.getOriginalFilename();
	            String modifiedBname = productDto.getPname().replaceAll(" ", "_");
	            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
	            String newFileName = "product_" + modifiedBname + "_" + timestamp + fileExtension;
	
	            String newFilePath = imageDirectory + "/image/" + newFileName;
	            newImage.transferTo(new File(newFilePath));
	            System.out.println(newFilePath);
	            System.out.println("New image file saved: " + newFilePath);
	            existingProduct.setImage("/image/" + newFileName);
	        }
	    	
	    	if(newImages !=null && !newImages.isEmpty())
	    	{
	    		List<String> oldImagesUrl = existingProduct.getImages();
	    		if(oldImagesUrl !=null)
	    		{
	    			for(String imageUrl :oldImagesUrl)
	    			{
	    				String oldFilePath = imageDirectory + imageUrl;
	    				File oldImageFile = new File(oldFilePath);
	    				if (oldImageFile.exists() && oldImageFile.delete()) 
	    				{
			                System.out.println("List of old images deleted successfully. "+existingProduct.getPname());
			            }  
	    			}
	    		}
	    		
	    		int i = 1;
	            List<String> productImages = new ArrayList<String>();
	            for(MultipartFile file: newImages)
	            {
	            	String multiFileName = file.getOriginalFilename();
	            	String timestamp = String.valueOf(System.currentTimeMillis());
	            	System.out.println(multiFileName);
	            	String mutliImageName= productDto.getPname();
	            	String multimodifiedPname = mutliImageName.replaceAll(" ", "_");
	            	String multiFileExtension = multiFileName.substring(multiFileName.lastIndexOf("."));
	                String newMultiFileName = "product_"+multimodifiedPname+"_"+i+"_"+timestamp+multiFileExtension;
	                String mutliFilePath = uploadDirectory + File.separator + newMultiFileName;
	                try 
	                {
	                   file.transferTo(new File(mutliFilePath));
	                } 
	                catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	                productImages.add("/image/"+newMultiFileName);
	                i++;
	            }
	            existingProduct.setImages(productImages);
	    	}
	    	productService.addProduct(existingProduct);    	
	    }
	    return "redirect:/product";
	}
	
}
