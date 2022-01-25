package com.spring.docker.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(

        name = "course_title",
        columnNames = "title"

))
public class Course {

    @Id
    @SequenceGenerator(

            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1

    )
    @GeneratedValue(

            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"

    )
    private Long courseId;

    @Column(nullable = false)
    private String title;
    private Integer credit;

    @OneToOne(

            mappedBy = "course"

    )
    private CourseMaterial courseMaterial;

    public Course() {
    }

    public Course(Long courseId, String title, Integer credit, CourseMaterial courseMaterial) {
        this.courseId = courseId;
        this.title = title;
        this.credit = credit;
        this.courseMaterial = courseMaterial;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public CourseMaterial getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(CourseMaterial courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", title='" + title + '\'' +
                ", credit=" + credit +
                ", courseMaterial=" + courseMaterial +
                '}';
    }
}
