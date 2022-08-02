package com.student.Student.specification.service;

import com.student.Student.specification.entity.Student;
import com.student.Student.specification.exception.AdminException;
import com.student.Student.specification.repository.StudentRepository;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceImpl {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private EntityManager entityManager;



    public String updateById(Long id){
        Optional<Student> student = repository.findById(id);
        if(student.isPresent()) {
            student.get().setDeleted(true);
            repository.save(student.get());
        } else if (!student.isPresent()){
            return "record not found";
        }
        String s = "user is deleted";
        return s;
    }

    @Override
    public Iterable<Student> getAllSoftDeletedStudent(Boolean isDeleted) {
        Session session=entityManager.unwrap(Session.class);
        Filter filter= session.enableFilter("deletedStudentFilter");
        filter.setParameter("isDeleted",isDeleted);
        Iterable<Student> iterable=repository.findAll();
        session.disableFilter("deletedStudentFilter");
        return  iterable;
    }

    public List<Student>getAllStudentInfo(Pageable pageRequest,String department)throws AdminException {
        List<Student> studentList = repository.paginatingStudent(pageRequest, department);

        return studentList;

    }



//    public Object updateById(Long id, Student student) {
//        return repository.save(student);
//
//    }
}
