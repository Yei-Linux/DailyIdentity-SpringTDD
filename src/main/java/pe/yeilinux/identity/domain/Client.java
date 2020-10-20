package pe.yeilinux.identity.domain;

import lombok.Data;

@Data
public class Client {
    private String client_id;
    private String description;
    private String resource_ids;
    private String client_secret;
    private String scope;
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    private String authorities;
    private int access_token_validity;
    private int refresh_token_validity;
    private String additional_information;
    private String autoapprove;
    private int tokenId;
}
