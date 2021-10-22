package com.ibm.coursefinder.repositories;

import com.ibm.coursefinder.userroles.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}