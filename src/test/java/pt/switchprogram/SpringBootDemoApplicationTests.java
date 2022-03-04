package pt.switchprogram;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {SpringBootDemoApplicationTests.Initializer.class})
public class SpringBootDemoApplicationTests {

	@ClassRule
	public static JdbcDatabaseContainer mysql = new MySQLContainer()
			.withDatabaseName("switch")
			.withUrlParam("useSSL", "false")
			.withUrlParam("allowPublicKeyRetrieval", "false");

	@Test
	public void contextLoads() {
	}

	static class Initializer
			implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of(
					"spring.datasource.url=" + mysql.getJdbcUrl(),
					"spring.datasource.username=" + mysql.getUsername(),
					"spring.datasource.password=" + mysql.getPassword()
			).applyTo(configurableApplicationContext.getEnvironment());
		}
	}

}
