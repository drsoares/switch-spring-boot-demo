package pt.switchprogram.wiring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.switchprogram.repositories.InMemoryStudentRepo;
import pt.switchprogram.repositories.StudentRepository;

@Configuration
public class Wiring {
    @Bean
    StudentRepository getStudentsRepo() {
        return new InMemoryStudentRepo();
    }
}
