package com.student.Student.specification.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private Date createdAt=new Date();
    private Date updatedAt=new Date();
}
