package pe.yeilinux.identity.unitTest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pe.yeilinux.identity.domain.User;
import pe.yeilinux.identity.factory.FactoriesTest;
import pe.yeilinux.identity.repository.impl.UserRepositoryImpl;
import pe.yeilinux.identity.service.impl.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepositoryImpl userRepository;

    @Test
    public void find_by_username_when_exist(){
        String userName = "jesusalvan2010@gmail.com";
        String password = "123";

        when(this.userRepository.findByUserName(userName)).thenReturn(FactoriesTest.get_user_object(userName,password));

        User result = this.userService.findByUserName(userName);

        assertEquals(FactoriesTest.get_user_object(userName,password).getUserName(),result.getUserName());
        assertEquals(FactoriesTest.get_user_object(userName,password).getPassword(),result.getPassword());
    }

    @Test
    public void find_by_username_when_not_exist(){
        String userName = "jesusalvan2011@gmail.com";

        when(this.userRepository.findByUserName(userName)).thenReturn(null);

        User result = this.userService.findByUserName(userName);

        assertNull(result);
    }
}
