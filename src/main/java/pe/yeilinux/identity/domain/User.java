package pe.yeilinux.identity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    public String userName;
    public String phoneNumber;
    public String password;
    public Person personDetails = new Person();
}
