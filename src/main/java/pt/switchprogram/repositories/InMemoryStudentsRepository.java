package pt.switchprogram.repositories;

import org.springframework.stereotype.Repository;
import pt.switchprogram.domain.Student;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryStudentsRepository implements StudentsRepository {

    private AtomicLong atomicLong = new AtomicLong();
    private final Map<Long, Student> studentMap = new ConcurrentHashMap<>();

    @Override
    public Collection<Student> findAll() {
        return studentMap.values();
    }

    @Override
    public Student save(Student student) {
        if (Objects.isNull(student.getId())) {
            Long studentId = atomicLong.incrementAndGet();
            student.setId(studentId);
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Optional<Student> findById(long id) {
        return Optional.ofNullable(studentMap.get(id));

    }

    @Override
    public void delete(long id) {
        studentMap.remove(id);
    }
}
