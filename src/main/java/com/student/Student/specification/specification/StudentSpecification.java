package com.student.Student.specification.specification;

import com.student.Student.specification.entity.Student;
import com.student.Student.specification.repository.StudentCustomRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class StudentSpecification {
    public static Specification<Student> hasFirstName(String firstName){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("firstName"),"%" + firstName + "%");
        });
    }
    public static Specification<Student> containsLastName(String lastName){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(root.get("lastName"),"%"+lastName +"%");
        });
    }
    public static Specification<Student> hasDepartment(String department){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("department"),department);
        });
    }

}
