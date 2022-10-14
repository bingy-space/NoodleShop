package com.noodleshop.noodleshopbackend.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.noodleshop.noodleshopbackend.entity.Country;
import com.noodleshop.noodleshopbackend.entity.Product;
import com.noodleshop.noodleshopbackend.entity.ProductCategory;
import com.noodleshop.noodleshopbackend.entity.State;
 
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	private EntityManager entityManager;
	
	// Autowire JPA entity manager
	@Autowired
	public MyDataRestConfig(EntityManager thEntityManager) {
		entityManager = thEntityManager;
	}
 
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
 
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
 
        // disable HTTP methods for Product, ProductCategory, Country, and State: PUT, POST and DELETE
        disableHttpMethods(Product.class,config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class,config, theUnsupportedActions);
        disableHttpMethods(Country.class,config, theUnsupportedActions);
        disableHttpMethods(State.class,config, theUnsupportedActions);
 
        // call an internal helper method
        exposeId(config);
    }

	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}

	private void exposeId(RepositoryRestConfiguration config) {
		// Expose entity ids
		
		// Get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities(); 
		
		// Create an array of the entity types
		List<Class> entityClasses = new ArrayList<>();
		
		// Get the entity types for the entities
		for(EntityType tempEntityType: entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// Expose the entity ids for the array of entity/domin types
		Class[] dominTypes = entityClasses.toArray(new Class[0]); 
		config.exposeIdsFor(dominTypes);
	}
}