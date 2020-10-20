package pe.yeilinux.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.yeilinux.identity.domain.Token;
import pe.yeilinux.identity.repository.TokenRepository;
import pe.yeilinux.identity.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public Token getSignKeyAndVerifierKey() {
        return this.tokenRepository.getSignKeyAndVerifierKey();
    }

    @Override
    public void updateSignKeyAndVerifierKey(Token token) {
        this.tokenRepository.updateSignKeyAndVerifierKey(token);
    }

    @Override
    public int createToken() {
        return this.tokenRepository.createToken();
    }
}
