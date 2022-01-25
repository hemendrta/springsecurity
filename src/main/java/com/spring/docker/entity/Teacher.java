package com.spring.docker.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {

    @Id
    @SequenceGenerator(

            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1

    )
    @GeneratedValue(

            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"

    )
    private Long teacherId;
    private String firstName;
    private String lastName;

    @OneToMany(

            cascade = CascadeType.ALL

    )
    @JoinColumn(

            name = "teacher_id",
            referencedColumnName = "teacherId"

    )
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(Long teacherId, String firstName, String lastName, List<Course> courses) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courses=" + courses +
                '}';
    }
}
