package com.solventek.service;

import java.util.List;

import com.solventek.entity.Product;



public interface ProductService {
	
	public List<Product> getProducts();
	public Product addProduct(Product product);
	public Product updateProduct(Product product,Integer id);
	public Product getProductByid(Integer id);
	public void deleteProduct(Integer id);

}
