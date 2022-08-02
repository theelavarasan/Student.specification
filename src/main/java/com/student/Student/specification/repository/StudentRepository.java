package com.student.Student.specification.repository;

import com.student.Student.specification.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long>,StudentCustomRepository, JpaSpecificationExecutor<Student> {


    Optional<Student> findById(Long id);

    @Query(value="SELECT c FROM Student c WHERE c.department like %:department%")
    List<Student> paginatingStudent(Pageable pageRequest, String department);



//    List<String> deletestudent(Long id);
}
