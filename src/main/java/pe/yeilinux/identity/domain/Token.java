package pe.yeilinux.identity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Token {
    private int id;
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String jti;
    private String additionalInformation;
    private String signingKey;
    private String verifierKey;
}
