package pe.yeilinux.identity.unitTest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import pe.yeilinux.identity.domain.Token;
import pe.yeilinux.identity.factory.FactoriesTest;
import pe.yeilinux.identity.repository.impl.TokenRepositoryImpl;
import pe.yeilinux.identity.service.TokenService;
import pe.yeilinux.identity.service.impl.TokenServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @InjectMocks
    TokenServiceImpl tokenService;

    @Mock
    TokenRepositoryImpl tokenRepository;

    @Test
    public void get_signing_key_and_verifier_key(){
        when(this.tokenRepository.getSignKeyAndVerifierKey()).thenReturn(FactoriesTest.get_token("","","",0,"","","123","123"));
        Token result = this.tokenService.getSignKeyAndVerifierKey();
        assertEquals("123",result.getSigningKey());
        assertEquals("123",result.getSigningKey());
    }
}
