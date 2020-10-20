package pe.yeilinux.identity.service;

import pe.yeilinux.identity.domain.Token;

public interface TokenService {
    public Token getSignKeyAndVerifierKey();
    public void updateSignKeyAndVerifierKey(Token token);
    public int createToken();
}
