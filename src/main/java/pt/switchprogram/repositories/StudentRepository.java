package pt.switchprogram.repositories;

import pt.switchprogram.domain.Student;

import java.util.Collection;
import java.util.Optional;

public interface StudentRepository {
    Collection<Student> findAll();

    Student save(Student student);

    Optional<Student> findById(long id);

    void deleteById(long id);
}
