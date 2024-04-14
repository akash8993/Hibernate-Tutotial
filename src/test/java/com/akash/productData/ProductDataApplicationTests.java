package com.akash.productData;

import com.akash.productData.entity.Employee;
import com.akash.productData.entity.Product;
import com.akash.productData.repository.EmployeeRepository;
import com.akash.productData.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	//Now performing Data Finder Methods

	@Test
	public void findMethods()
	{
		List<Product> product=productRepository.findByName("Washer");
		product.stream().forEach(x->System.out.println(x.getName()));

		List<Product> productsList= productRepository.findByNameAndDescription("Washer","Washer Form LG");
		productsList.stream().forEach(x->System.out.println(x.getName()+" "+x.getPrice()));

		List<Product> productList2= productRepository.findByPriceGreaterThan(450d);
		productList2.stream().forEach(x-> System.out.println(x.getName()+" "+x.getPrice()));

		List<Product> productList3= productRepository.findByDescriptionContains("Awesome");
		productList3.stream().forEach(x-> System.out.println(x.getName()+" "+x.getDescription()));

		List<Product> productList4= productRepository.findByPriceBetween(200d,1500d);
		productList4.stream().forEach(x-> System.out.println(x.getName()+" "+x.getPrice()));

		List<Product> findByDescLike= productRepository.findByDescriptionLike("Awesome ");
		findByDescLike.stream().forEach(x-> System.out.println(x.getName()+" "+x.getDescription()));

		List<Product> findByIdIn= productRepository.findByIdIn(Arrays.asList(1,2,3));
		findByIdIn.stream().forEach(x-> System.out.println(x.getName()+" "+x.getId()));

	}
}
