package com.spring.docker.repository;

import com.spring.docker.entity.Student;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String name);

    public List<Student> findByGuardianEmail(String email);

    public Student findByFirstNameAndEmailId(String firstName, String email);

//    JPQL
    @Query("select s from Student s where s.emailId = ?1")
    public Student findByEmailAddress(String email);

    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String findByEmailAddressFirstName(String email);

//    Native Queries
    @Query(

            value = "select * from tbl_student s where s.email_address = ?1 ",
            nativeQuery = true

    )
    public Student findByEmailAddressFirstNameNative(String email);

//    Native Named Parameter

    @Query(

            value = "select * from tbl_student s where s.email_address = :emailId",
            nativeQuery = true

    )
    public Student getStudentByEmailAddress(@Param("emailId") String emailId);

//    Data Manipulation

    @Modifying
    @Transactional
    @Query(

            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true

    )
    public void updateStudentNameByEmailId(String firstName, String emailId);

}
