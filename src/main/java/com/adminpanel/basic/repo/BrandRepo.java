package com.adminpanel.basic.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adminpanel.basic.model.Brand;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer>
{

}
