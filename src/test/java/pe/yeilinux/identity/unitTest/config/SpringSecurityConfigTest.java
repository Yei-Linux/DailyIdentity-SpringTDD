package pe.yeilinux.identity.unitTest.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.yeilinux.identity.config.SpringSecurityConfig;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SpringSecurityConfigTest {

    @InjectMocks
    SpringSecurityConfig springSecurityConfig;

    @Test
    public void correct_password_encrypted(){
        BCryptPasswordEncoder passwordEncoder = this.springSecurityConfig.passwordEncoder();
        System.out.println(passwordEncoder.toString());
    }

}
