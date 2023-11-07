package com.adminpanel.basic.controller;

import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.servlet.view.RedirectView;

import com.adminpanel.basic.model.Brand;
import com.adminpanel.basic.model.Employee;
import com.adminpanel.basic.model.Product;
import com.adminpanel.basic.service.BrandService;
import com.adminpanel.basic.service.EmployeeService;
import com.adminpanel.basic.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/home")
	public String dashboard(HttpServletRequest request, Model model)
	{
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null)
		{
			// User is authenticated, display dashboard
			 List<Employee> employee = (List<Employee>) employeeService.getAll();
			 model.addAttribute("employees", employee); 
			 return "home";
		} 
		else
		{
			// User is not authenticated, redirect to the login page
			return "redirect:/login";
		}
	}
	
	/*
	 * @PostMapping("/add") public ResponseEntity<Employee> save(@RequestBody
	 * Employee employee) { return ResponseEntity.ok(employeeService.save(employee))
	 * ; }
	 */

	  @RequestMapping("/add") 
	  public String add(HttpServletRequest request)
	  {
		  HttpSession session = request.getSession(false);
		  if (session != null && session.getAttribute("username") != null)
		  {
			  return "addemployee";
		  }
		  else
		  {
		  //User is not authenticated, redirect to the login page 
			  return "redirect:/login"; 
		  }
	  }
	 
	 @RequestMapping(path = "/addform", method = RequestMethod.POST)
	 public RedirectView add(@ModelAttribute Employee employee) 
	 {
		  System.out.println(employee); 
		  String plainpassword = employee.getPassword();
		  String bcryptpassword = BCrypt.hashpw(plainpassword, BCrypt.gensalt());
		  employee.setPassword(bcryptpassword);
		  employeeService.save(employee); 
		  RedirectView redirectView = new RedirectView(); 
		  redirectView.setUrl("/home"); 
		  return redirectView; 
	  }
	 

	@RequestMapping(path = "/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) 
	{
		employeeService.deleteDetial(id);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/home");
		return redirectView;
	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") int id, Model model,HttpServletRequest request) 
	{
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null)
		{
			Optional<Employee> emp = employeeService.getDetails(id);
			Employee emp1 = emp.get();
			model.addAttribute("employees", emp1);
			return "updateform";
		} 
		else
		{
			// User is not authenticated, redirect to the login page
			return "redirect:/login";
		}
		
	}

	@PostMapping("/updateform")
	public RedirectView updateDetials(@ModelAttribute Employee employee) 
	{
		employeeService.save(employee);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/home");
		return redirectView;
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/loginaccess")
	public String dashboard(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password)
		{	
		System.out.println(username);
		System.out.println(password);
		if (username.equals("admin@gmail.com") && password.equals("123456")) 
		{
			HttpSession session = request.getSession();
			request.getSession().setAttribute("isLoggedIn", "true");
			System.out.println(session);
			session.setAttribute("username", username);
			return "redirect:/home";		
		}
		else
		{
			return "redirect:/login";
		}
	}

	// logout
  	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if (session != null) 
		{
			session.invalidate();
		}
		request.getSession().setAttribute("isLoggedIn", "false");

		return "logout";
	}
  	
  	@GetMapping("/product")
  	public String productPage(Model model)
  	{
  		List<Product> products = productService.getAll();
  		model.addAttribute("products", products);
  		for(Product product:products)
  		{
  			System.out.println(product);
  		}
  		return "product";
  	}
  	
  	@RequestMapping("/addproduct")
  	public String addProductPage()
  	{
  		return "addproduct";
  	}
  	
  	@RequestMapping("/brand")
  	public String brandPage(Model model)
  	{
  		List<Brand> brands = brandService.getAll();
  		model.addAttribute("brands", brands);
  		for(Brand brand:brands)
  		{
  			System.out.println(brand);
  		}
  		return "brand";
  	}
  	
  	@RequestMapping("/addbrand")
  	public String addBrandPage()
  	{
  		return "addbrand";
  	}
  	
}  