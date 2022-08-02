package com.student.Student.specification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "Update student SET deleted=true WHERE id=?")
//@Where(clause = "deleted=false ")
@FilterDef(name = "deletedStudentFilter",parameters = @ParamDef(name = "isDeleted",type = "boolean"))
@Filter(name = "deletedStudentFilter",condition = "deleted= :isDeleted")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Department")
    private String department;
    private Date createdAt=new Date();
    private Date updatedAt=new Date();
    @Column(name = "deleted")
    private Boolean deleted=false;


    public boolean isDeleted() {
        return deleted;
    }
//
//    public void setDeleted(boolean deleted) {
//        this.deleted = deleted;
//    }


}
