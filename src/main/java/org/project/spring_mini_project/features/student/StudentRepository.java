package org.project.spring_mini_project.features.student;

import org.project.spring_mini_project.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface StudentRepository extends JpaRepository<Student, Integer>
{
    Optional<Student> findByUserUsername(String username);
    List<Student> findByUserId(Long userId);
}
