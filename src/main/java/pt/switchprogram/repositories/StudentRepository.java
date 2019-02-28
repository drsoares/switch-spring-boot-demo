package pt.switchprogram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.switchprogram.domain.Course;
import pt.switchprogram.domain.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentByPreviousCourse(Course course);
}
