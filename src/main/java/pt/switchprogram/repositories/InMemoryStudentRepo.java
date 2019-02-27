package pt.switchprogram.repositories;

import pt.switchprogram.domain.Student;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryStudentRepo implements StudentRepository {

    private final AtomicLong atomicLong = new AtomicLong();
    private final Map<Long, Student> studentMap = new HashMap<>();

    @Override
    public Collection<Student> findAll() {
        return studentMap.values();
    }

    @Override
    public Student save(Student student) {
        if (Objects.isNull(student.getId())) {
            long id = atomicLong.incrementAndGet();
            student.setId(id);
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(long id) {
        return Optional.ofNullable(studentMap.get(id));
    }

    @Override
    public void deleteById(long id) {
        studentMap.remove(id);
    }
}
