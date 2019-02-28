package pt.switchprogram.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.switchprogram.domain.Course;
import pt.switchprogram.domain.Student;
import pt.switchprogram.repositories.StudentRepository;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
public class StudentsController {

    private final StudentRepository studentRepository;

    public StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public Collection<Student> students(@RequestParam(value = "course", required = false) Course course) {
        return Optional.ofNullable(course)
                .map(studentRepository::findStudentByPreviousCourse)
                .orElse(studentRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();
        return ResponseEntity.created(location).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (!studentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Student existingStudent = studentOptional.get();
            existingStudent.setName(student.getName());
            existingStudent.setPreviousCourse(student.getPreviousCourse());
            studentRepository.save(existingStudent);
            return ResponseEntity.ok(existingStudent);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }

}
