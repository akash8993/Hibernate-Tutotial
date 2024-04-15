package com.akash.productData.repository;

import com.akash.productData.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("from Student")
    List<Student> findAllStudents();

    //Read Partial Data
    @Query("Select firstName, lastName from Student st")
    List<Object []> findAllStudentPartialData();

    @Query("from Student where firstName=:firstName")
    List<Student> findAllStudentsByFirstName(@Param("firstName") String firstName);

    @Query("from Student where score>=:min and score<=:max")
    List<Student> findAllStudentsByScore(@Param("min") int min, @Param("max") int max);

    //This below is used bocz JPA thinks that these operations are just read ones so for that we use @Modyfying
    @Modifying
    @Query("delete from Student where firstName=:firstName")
    public void deleteStudentByName(@Param("firstName") String firstName);
}
