package pe.yeilinux.identity.controller.request;

import lombok.Data;
import pe.yeilinux.identity.domain.Company;
import pe.yeilinux.identity.domain.User;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private String address;
    private int typeUserId;
    private int genderId;
    private User user;
    private Company company;
}
