package com.ibm.coursefinder.repositories;

import com.ibm.coursefinder.userroles.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}