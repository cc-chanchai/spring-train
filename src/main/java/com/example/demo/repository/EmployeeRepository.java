package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select o from Employee o join o.titleName t where o.name=:name and t.code='01'")
    List<Employee> findByName(@Param("name") String name);

    @Query("select o from Employee o where o.name like CONCAT('%',:name,'%')")
    List<Employee> findByNameLike(@Param("name")String name);

    Employee findByLastName(@Param("lastName")String last);

}
