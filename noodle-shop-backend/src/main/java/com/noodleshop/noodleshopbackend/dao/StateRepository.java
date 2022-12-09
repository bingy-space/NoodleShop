package com.noodleshop.noodleshopbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.noodleshop.noodleshopbackend.entity.State;

//@CrossOrigin("http://localhost:4200")
@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer>{
	
	// To retrieve states for a given country code
	// http://localhost:8080/api/states/search/findByCountryCode?code=IN
	List<State> findByCountryCode(@Param("code") String code);

}
