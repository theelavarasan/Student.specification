package com.student.Student.specification.repository;

import com.student.Student.specification.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class StudentCustomRepositoryImpl implements StudentCustomRepository{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> findByFirstNameAndDepartment(String firstName, String department) {
        CriteriaBuilder cb =entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Student.class);
        Root<Student> student =cq.from(Student.class);

        Predicate firstNamePredicate=cb.equal(student.get("firstName"),firstName);
        Predicate departmentPredicate=cb.equal(student.get("department"),department);

        cq.where(firstNamePredicate,departmentPredicate);

        TypedQuery<Student> query=entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Student> findByDateAndName(Date createdAt, String firstName) {
        CriteriaBuilder cb =entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Student.class);
        Root<Student> student =cq.from(Student.class);

        Predicate createdAtPredicate=cb.greaterThanOrEqualTo(student.get("createdAt"), createdAt);
//        Predicate updatedAtPredicate=cb.lessThanOrEqualTo(student.get("updatedAt"),updatedAt);
        Predicate firstNamePredicate=cb.like(student.get("firstName"),"%" + firstName + "%");

        cq.where(createdAtPredicate,firstNamePredicate);

        TypedQuery<Student> query=entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Student> findByDateBetween(Date createdAt, Date updatedAt) {
        CriteriaBuilder cb =entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Student.class);
        Root<Student> student =cq.from(Student.class);

        Predicate createdAtPredicate=cb.greaterThanOrEqualTo(student.get("createdAt"),createdAt);
        Predicate updatedAtPredicate=cb.lessThanOrEqualTo(student.get("updatedAt"),updatedAt);

        cq.where(createdAtPredicate,updatedAtPredicate);

        TypedQuery<Student> query=entityManager.createQuery(cq);
        return query.getResultList();
    }




    @Override
    public void deleteByid(int id) {

    }

//    @Override
//    public Optional<Student> findByUserId(int id) {
//        return Optional.empty();
//    }


}
