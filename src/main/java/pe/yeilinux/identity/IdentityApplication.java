package pe.yeilinux.identity;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "pe.yeilinux.identity")
@EnableResourceServer
public class IdentityApplication {
	public static void main(String[] args) {
		SpringApplication.run(IdentityApplication.class, args);
	}

	@Bean(name = "developmentDataSource")
	@ConfigurationProperties(prefix="spring.datasource.db-identity-development")
	public DataSource identityDevelopmentDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "testDataSource")
	@ConfigurationProperties(prefix="spring.datasource.db-identity-test")
	@Primary
	public DataSource identityTestDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "developmentJdbcTemplate")
	public JdbcTemplate identityDevelopmentJdbcTemplate(@Qualifier("developmentDataSource") DataSource devDataSource){
		return new JdbcTemplate(devDataSource);
	}

	@Bean(name = "testJdbcTemplate")
	public JdbcTemplate identityTestJdbcTemplate(@Qualifier("testDataSource") DataSource testDataSource){
		return new JdbcTemplate(testDataSource);
	}

	@Bean
	public FlywayMigrationInitializer flywayInitializerOne(@Qualifier("flywayDev") Flyway flywayDev) {
		return new FlywayMigrationInitializer(flywayDev, null);
	}

	@Bean
	@Primary
	public FlywayMigrationInitializer flywayInitializerTwo(@Qualifier("flywayTest") Flyway flywayTest) {
		return new FlywayMigrationInitializer(flywayTest, null);
	}
}
