package com.akash.productData;

import com.akash.productData.entity.Employee;
import com.akash.productData.entity.Product;
import com.akash.productData.entity.Student;
import com.akash.productData.repository.EmployeeRepository;
import com.akash.productData.repository.ProductRepository;
import com.akash.productData.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;
@SpringBootTest
class ProductDataApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private StudentRepository studentRepository;
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

	//Now performing Data Finder Methods -- JPA Wale

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

	//PagingAndSortingRepository is the child interface of CRUD Repository
	/*For Paging And Sorting Repository
	using methods to test Paging and Sorting
	* */
	@Test
	public void pagingAndSorting()
	{
		//Pageable is an interface
		Pageable pageable= PageRequest.of(1,3);

		Page<Product> data= productRepository.findAll(pageable);

		data.stream().forEach(x-> System.out.println(x.getName()+" "+x.getDescription()));
	}

	@Test
	public void Sorting()
	{
		productRepository.findAll(Sort.by(Sort.Direction.DESC,"name")).forEach(x-> System.out.println(x.getName()));

		productRepository.findAll(Sort.by(Sort.Direction.DESC, "name","price")).forEach(x-> System.out.println(x.getName()+" "+x.getPrice()));

		//Ye bcoz apan ko dono property alag alag sort chahie
		//Isme order class use hoti hai bcoz order leti hai property desc or not
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

	//FOR THE JPQL TEsting
	@Test
	public void addStudent()
	{
		Student student= Student.builder()
				.firstName("Akash")
				.lastName("Gupta")
				.score(100)
				.id(1)
				.build();
		studentRepository.save(student);
	}

	@Test
	public void fetchAllStudents()
	{
		studentRepository.findAllStudents().stream().forEach(x-> System.out.println(x.getFirstName()+" "+x.getLastName()));

		/*Jab bhi partial data get krenge to Object [] aaega fir ek ek column se data leneg*/

		List<Object[]> data=studentRepository.findAllStudentPartialData();
		for(Object [] objects: data)
		{
			System.out.println(objects[0]+" 2nd column "+ objects[1]);
		}

	}

	@Test
	public void findStudentByFirstName()
	{
		System.out.println(studentRepository.findAllStudentsByFirstName("Akash"));
	}
	@Test
	public void findStudentByScore()
	{
		System.out.println(studentRepository.findAllStudentsByScore(20,100));
	}
	
	//RollBack bcoz we dont want to get back the data
	@Test
	@Transactional
	@Rollback(value = false)
	public void deleteStudent()
	{
	studentRepository.deleteStudentByName("rer");
	}

}
