package pe.yeilinux.identity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class TokenBody {
    private int exp;
    private String user_name;
    private String jti;
    private String client_id;
    private List<String> scope;
}
