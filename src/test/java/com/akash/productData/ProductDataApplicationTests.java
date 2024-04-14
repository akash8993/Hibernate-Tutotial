package com.akash.productData;

import com.akash.productData.entity.Employee;
import com.akash.productData.entity.Product;
import com.akash.productData.repository.EmployeeRepository;
import com.akash.productData.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
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

	/*For Paging And Sorting Repository
	using methods to test Paging and Sorting
	* */
	@Test
	public void pagingAndSorting()
	{
		Pageable pageable= PageRequest.of(0,3);

		Page<Product> data= productRepository.findAll(pageable);

		data.stream().forEach(x-> System.out.println(x.getName()+" "+x.getDescription()));
	}

	@Test
	public void Sorting()
	{
		productRepository.findAll(Sort.by(Sort.Direction.DESC,"name")).forEach(x-> System.out.println(x.getName()));

		productRepository.findAll(Sort.by(Sort.Direction.DESC, "name","price")).forEach(x-> System.out.println(x.getName()+" "+x.getPrice()));

		productRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "name"), new Sort.Order(Sort.Direction.ASC,"price"))).forEach(x-> System.out.println(x.getName()+" "+x.getPrice()));

	}

	@Test
	public void pagingAndSortingFindAll()
	{
		Pageable pageable= PageRequest.of(0,2, Sort.Direction.DESC, "name");
		productRepository.findAll(pageable).forEach(x-> System.out.println(x.getName()+" "+x.getPrice()));

	}

	@Test
	public void PageableRequestInRepo()
	{
		Pageable pageable= PageRequest.of(0,2);
		List<Product> products= productRepository.findByIdIn(Arrays.asList(1,2,3),pageable);
		products.stream().forEach(x-> System.out.println(x.getName()+" "+x.getPrice()));
	}
}
