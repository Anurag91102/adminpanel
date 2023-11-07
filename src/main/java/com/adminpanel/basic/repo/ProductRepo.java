package com.adminpanel.basic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.adminpanel.basic.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> 
{

}
