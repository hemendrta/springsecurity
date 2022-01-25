package com.spring.docker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_student",

        uniqueConstraints = @UniqueConstraint(

                name = "emailid_unique",
                columnNames = "email_address"

        )

)
public class Student {

    @Id
    @SequenceGenerator(

            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1

    )

    @GeneratedValue(

            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"

    )
    private Long studentId;
    private String firstName;
    private String lastName;

    @Column(name = "email_address",
        nullable = false
    )
    private String emailId;

    @Embedded
    private Guardian guardian;

    public Student() {
    }

    public Student(Long studentId, String firstName, String lastName, Guardian guardian, String emailId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardian = guardian;
        this.emailId = emailId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", guardian=" + guardian +
                '}';
    }
}
