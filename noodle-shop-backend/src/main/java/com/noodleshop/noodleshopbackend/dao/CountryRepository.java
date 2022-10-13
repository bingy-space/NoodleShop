package com.noodleshop.noodleshopbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.noodleshop.noodleshopbackend.entity.Country;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "countries", path="countries") // Expose /countries endpoint
public interface CountryRepository extends JpaRepository<Country, Integer>{

}
