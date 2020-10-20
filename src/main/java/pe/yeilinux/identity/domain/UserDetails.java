package pe.yeilinux.identity.domain;

import lombok.Data;

@Data
public class UserDetails {
    private int companyId;
    private String companyName;
    private int tokenId;
}
