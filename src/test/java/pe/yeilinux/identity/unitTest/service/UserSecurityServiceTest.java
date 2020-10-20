package pe.yeilinux.identity.unitTest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pe.yeilinux.identity.factory.FactoriesTest;
import pe.yeilinux.identity.service.impl.UserSecurityService;
import pe.yeilinux.identity.service.impl.UserServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserSecurityServiceTest {

    @InjectMocks
    UserSecurityService userSecurityService;

    @Mock
    UserServiceImpl userService;

    @Test
    public void load_by_username_when_is_correct_credentials(){
        String username = "jesusalvan2010@gmail.com";
        String password = "123";

        when(this.userService.findByUserName(username)).thenReturn(FactoriesTest.get_user_object(username,password));

        UserDetails result = this.userSecurityService.loadUserByUsername(username);

        assertEquals(new User(username,"123",new ArrayList<>()).getUsername(),result.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void not_load_username_when_is_incorrect_credentials(){
        String username = "jesusalvan2011@gmail.com";

        when(this.userService.findByUserName(username)).thenReturn(null);

        UserDetails result = this.userSecurityService.loadUserByUsername(username);
    }

}
