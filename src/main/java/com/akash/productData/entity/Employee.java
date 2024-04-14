package com.akash.productData.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

 //The below 2 are used when we want to create a new table that will be having the id and associated data
   // @TableGenerator(name = "employee_gen", table = "id_gen",
   //         pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 100)
   // @GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_gen")

 //Now below is used when we want to get unique id and we have created a randomId generator for that
    @Id
    @GenericGenerator(name = "emp_id", strategy = "com.akash.productData.generators.CustomRandomIdGenerator")
    @GeneratedValue(generator = "emp_id")
    private long id;
    private String name;
}
