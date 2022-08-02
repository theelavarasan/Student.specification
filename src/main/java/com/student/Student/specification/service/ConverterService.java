package com.student.Student.specification.service;

import com.student.Student.specification.dto.StudentDto;
import com.student.Student.specification.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterService {
    @Autowired
    private ModelMapper modelMapper;


    public StudentDto convertToDto(Student studentObject) {
        return modelMapper.map(studentObject, StudentDto.class);
    }

    public Student convertToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }
}
