package pe.yeilinux.identity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@EnableAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class IdentityApplicationTests {

	@Autowired
	WebApplicationContext context;

	@Autowired
	private Environment environment;

	@Test
	void configure_authorization_server_security_configurer() throws Exception {

	}

}
