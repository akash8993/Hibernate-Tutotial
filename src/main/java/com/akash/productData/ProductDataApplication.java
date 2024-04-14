package com.akash.productData;

import com.akash.productData.entity.Employee;
import com.akash.productData.entity.Product;
import com.akash.productData.repository.EmployeeRepository;
import com.akash.productData.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProductDataApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
//		Product product= Product.builder()
//				.id(1)
//				.price(1000d)
//				.name("Iphone22")
//				.description("Awesome")
//				.build();
//		productRepository.save(product);

//		Product product=productRepository.findById(1).orElseThrow(()-> new RuntimeException("Data Not found"));
//		System.out.println(product);

//		Employee employee=Employee.builder()
//				.name("ALOK222q1").build();
//		employeeRepository.save(employee);

		finderMethods();
	}

	private void finderMethods() {
		System.out.println(productRepository.findByName("Washer"));
	}
}
