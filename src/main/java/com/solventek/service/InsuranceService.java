package com.solventek.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solventek.entity.Product;
import com.solventek.repository.ProductRepository;

@Service
public class InsuranceService implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		product.setCategory("Insurance");
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product,Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.map(newProduct->{
					newProduct.setName(product.getName());
					newProduct.setPrice(product.getPrice());
					newProduct.setCategory("Health");
					productRepository.save(newProduct);
					return newProduct;
				}).orElseThrow(()->new NoSuchElementException("Product not found with the id "+id));
	}

	@Override
	public void deleteProduct(Integer id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

	@Override
	public Product getProductByid(Integer id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()->new NoSuchElementException("Product not found with the id"+id));
	}

}
