package com.student.Student.specification.repository;

import com.student.Student.specification.entity.Student;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface StudentCustomRepository {
    List<Student> findByFirstNameAndDepartment(String firstName, String department);


    List<Student> findByDateAndName(Date createdAt, String firstName);


    List<Student> findByDateBetween(Date createdAt, Date updatedAt);




    void deleteByid(int id);



}
