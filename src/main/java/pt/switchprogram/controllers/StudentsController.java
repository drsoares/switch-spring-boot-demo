package pt.switchprogram.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.switchprogram.domain.Student;
import pt.switchprogram.repositories.StudentsRepository;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentsController {

    private final StudentsRepository studentsRepository;

    public StudentsController(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @GetMapping
    public Collection<Student> retrieveStudents() {
        return studentsRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentsRepository.save(student);
        return ResponseEntity.ok(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateOrCreateStudent(@RequestBody Student student, @PathVariable long id) {
        Optional<Student> optionalStudent = studentsRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student oldStudent = optionalStudent.get();
            oldStudent.setName(student.getName());
            oldStudent.setPreviousCourse(student.getPreviousCourse());
            return ResponseEntity.ok(studentsRepository.save(oldStudent));
        } else {
            student.setId(id);
            return ResponseEntity.ok(studentsRepository.save(student));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentsRepository.delete(id);
    }
}
