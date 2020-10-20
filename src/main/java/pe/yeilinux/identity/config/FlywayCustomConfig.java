package pe.yeilinux.identity.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.yeilinux.identity.constant.Constant;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@AutoConfigureAfter({ DataSourceAutoConfiguration.class })
public class FlywayCustomConfig {
    @Resource(name = "developmentDataSource")
    private DataSource devDataSource;

    @Resource(name = "testDataSource")
    private DataSource testDataSource;

    @Bean(name = "flywayDev")
    public Flyway flywayOne() {
        return Flyway.configure().locations(Constant.CLASSPATHDEV).dataSource(this.devDataSource).load();
    }

    @Bean(name = "flywayTest")
    public Flyway flywayTwo() {
        return Flyway.configure().locations(Constant.CLASSPATHTEST).dataSource(this.testDataSource).load();
    }
}