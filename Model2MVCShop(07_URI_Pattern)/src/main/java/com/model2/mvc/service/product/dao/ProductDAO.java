package com.model2.mvc.service.product.dao;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;


public interface ProductDAO {
	

	public void insertProduct(Product product) throws Exception ;
		

	public Product findProduct(int product) throws Exception ;
		
	
	public List<Product> getProductList(Search search) throws Exception ;


	public void updateProduct(Product product) throws Exception ;
		
	
	public int getTotalCount(Search serach) throws Exception ;
		

}
