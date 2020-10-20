package pe.yeilinux.identity.repository;

import pe.yeilinux.identity.controller.request.UserRegisterRequest;
import pe.yeilinux.identity.domain.User;
import pe.yeilinux.identity.domain.UserDetails;

import java.util.List;

public interface UserRepository {
    public User findByUserName(String userName);
    public List<User> findUserByCompanyId(int companyId);
    public int createPerson(UserRegisterRequest userRegisterRequest);
    public void createUser(User userRequest,int typeUserId,int personId,int companyId);
    public UserDetails getUserDetails(String username);
}
