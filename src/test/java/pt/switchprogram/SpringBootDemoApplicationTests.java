package pt.switchprogram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import pt.switchprogram.controllers.StudentsController;
import pt.switchprogram.domain.Student;
import pt.switchprogram.repositories.StudentRepository;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:switch;MODE=MySQL;",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"driver-class-name=org.h2.Driver",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
public class SpringBootDemoApplicationTests {

	@Autowired
	private StudentRepository repository;

	@Test
	@Sql(scripts = "/database_setup.sql")
	public void shouldReturnStudents() {
		List<Student> students = repository.findAll();
		assertTrue(students.stream().anyMatch(s -> s.getName().equals("diogo")));
		assertTrue(students.stream().anyMatch(s -> s.getName().equals("ray")));
		assertTrue(students.stream().anyMatch(s -> s.getName().equals("matt")));
		assertTrue(students.stream().anyMatch(s -> s.getName().equals("sanjay")));
	}

}
