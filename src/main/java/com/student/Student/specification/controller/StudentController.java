package com.student.Student.specification.controller;

import com.student.Student.specification.entity.Student;
import com.student.Student.specification.exception.AdminException;
import com.student.Student.specification.repository.StudentRepository;
import com.student.Student.specification.service.ConverterService;
import com.student.Student.specification.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import static com.student.Student.specification.specification.StudentSpecification.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private StudentService service;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        return repository.save(student);
    }

    @GetMapping("/getAll")
    public List<Student> getALl(){
        return repository.findAll();
    }

    @GetMapping("/firstName/department")
    public List<Student>findByFirstNameAndDepartment(@RequestParam("firstName")String firstName, @RequestParam("department")String department){
        return repository.findAll(where(hasFirstName(firstName).or(hasDepartment(department))));
    }

    @GetMapping("/lastName")
    public List<Student> findByLastName(@RequestParam("lastName") String lastName){
        return repository.findAll(containsLastName(lastName));
    }

    @GetMapping("/department")
    public List<Student> findByDepartment(@RequestParam("department")String department){
        return repository.findAll(where(hasDepartment(department)));
    }
    @GetMapping("/firstName")
    public List<Student> findByFirstName(@RequestParam("firstName")String firstName){
        return repository.findAll(where(hasFirstName(firstName)));
    }
    @GetMapping("/date")
    public List<Student> findDateCreatedBetween(@RequestParam String createdAt, @RequestParam String updatedAt) throws ParseException {
        String sDate1 = ("createdAt,updatedAt");
        Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(createdAt);
        Date date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(updatedAt);
        System.out.println(sDate1+ "\t" + date1);
        return repository.findByDateBetween(date1,date2);
    }
        @GetMapping("/dateAndName")
    public List<Student> findByDateAndName(@RequestParam String createdAt,@RequestParam (value = "firstName") String firstName ) throws ParseException {
        String sDate1="createdAt";
        Date date1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(createdAt);
//        Date date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(updatedAt);
        System.out.println(sDate1+"\t"+date1);
        return repository.findByDateAndName(date1, firstName);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudentByid(@PathVariable(value = "id") int id){
        repository.deleteByid(id);
        return "Deleted Successfully";

    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        repository.deleteAll();
        return "Deleted Successfully";
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id){
        return ResponseEntity.ok(service.updateById(id));
    }

    @GetMapping("/getAllSoftDeletedStudent")
    public Iterable<Student> getAllSoftDeletedStudent(@RequestParam Boolean isDeleted){
        return service.getAllSoftDeletedStudent(isDeleted);
    }

    @GetMapping("/getAllStu")
    public Page<Student> getStudentInfo(@RequestParam("page") Integer page, @RequestParam("size") Integer size,@RequestParam String department) throws AdminException {
        PageRequest pagerequest = PageRequest.of(page,size);
        List<Student>studentPage=service.getAllStudentInfo(pagerequest,department );
        List<Student> studentResultList = studentPage;
        studentResultList = studentResultList.stream().map(studentObj -> {
            converterService.convertToDto(studentObj);
            return studentObj;
        }).collect(Collectors.toList());

        Page<Student> studentDtoPage = new PageImpl<>(studentResultList, pagerequest, studentPage.size());
        return studentDtoPage;

    }


}


