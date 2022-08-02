package com.student.Student.specification.service;

import com.student.Student.specification.entity.Student;

public interface StudentServiceImpl {
    Iterable<Student> getAllSoftDeletedStudent(Boolean isDeleted);


}
