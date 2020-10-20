package pe.yeilinux.identity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.yeilinux.identity.controller.request.UserRegisterRequest;

import pe.yeilinux.identity.domain.User;
import pe.yeilinux.identity.domain.UserDetails;
import pe.yeilinux.identity.factory.FactoriesDev;
import pe.yeilinux.identity.repository.UserRepository;
import pe.yeilinux.identity.service.ClientService;
import pe.yeilinux.identity.service.CompanyService;
import pe.yeilinux.identity.service.TokenService;
import pe.yeilinux.identity.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public List<User> findUserByCompanyId(int companyId) {
        return this.userRepository.findUserByCompanyId(companyId);
    }

    @Override
    public void createUser(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.getUser().setPassword(this.passwordEncoder.encode(userRegisterRequest.getUser().getPassword()));
        if( userRegisterRequest.getTypeUserId() == 1 && userRegisterRequest.getCompany().getToken_id() == 0){
            int tokenId = this.tokenService.createToken();
            userRegisterRequest.getCompany().setToken_id(tokenId);
            userRegisterRequest.getCompany().setId(this.companyService.createCompany(userRegisterRequest.getCompany()));
        }
        int personId = this.userRepository.createPerson(userRegisterRequest);
        this.userRepository.createUser(userRegisterRequest.getUser(),userRegisterRequest.getTypeUserId(),personId,userRegisterRequest.getCompany().getId());
    }

    @Override
    public UserDetails getUserDetails(String username) {
        return this.userRepository.getUserDetails(username);
    }
}
