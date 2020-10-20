package pe.yeilinux.identity.repository;

import pe.yeilinux.identity.domain.Token;

public interface TokenRepository {
    public Token getSignKeyAndVerifierKey();
    public void updateSignKeyAndVerifierKey(Token token);
    public int createToken();
}
