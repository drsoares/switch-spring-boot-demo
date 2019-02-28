package pt.switchprogram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.switchprogram.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
