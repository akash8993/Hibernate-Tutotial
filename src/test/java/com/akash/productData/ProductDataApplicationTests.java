package com.akash.productData;

import com.akash.productData.entity.Employee;
import com.akash.productData.entity.Product;
import com.akash.productData.repository.EmployeeRepository;
import com.akash.productData.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDataApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	@Test
	void contextLoads() {
	}


//	@Test
//	public void testCreate()
//	{
//		Product product=productRepository.findById(1).get();
//		product.setPrice(1200d);
//		productRepository.save(product);
//	}
	@Test
	public void testDel()
	{
		productRepository.deleteById(1);
	}

	@Test
	public void testCount()
	{
		System.out.println(productRepository.count());

	}

	@Test
	public void createEmployee()
	{
		Employee employee=Employee.builder()
				.id(1)
				.name("ADI")
				.build();
		employeeRepository.save(employee);
	}
}
