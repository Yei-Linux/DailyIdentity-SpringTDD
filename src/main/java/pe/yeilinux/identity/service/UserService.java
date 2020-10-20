package pe.yeilinux.identity.service;

import pe.yeilinux.identity.controller.request.UserRegisterRequest;
import pe.yeilinux.identity.domain.User;
import pe.yeilinux.identity.domain.UserDetails;

import java.util.List;

public interface UserService{
    public User findByUserName(String userName);
    public List<User> findUserByCompanyId(int companyId);
    public void createUser(UserRegisterRequest userRegisterRequest);
    public UserDetails getUserDetails(String username);
}
