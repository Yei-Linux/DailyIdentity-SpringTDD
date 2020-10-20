package pe.yeilinux.identity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Application {
    private String clientId;
    private String clientSecret;
    private String scopes;
}
