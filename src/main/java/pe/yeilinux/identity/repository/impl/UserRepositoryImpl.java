package pe.yeilinux.identity.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import pe.yeilinux.identity.constant.Constant;
import pe.yeilinux.identity.controller.request.UserRegisterRequest;
import pe.yeilinux.identity.domain.User;
import pe.yeilinux.identity.domain.UserDetails;
import pe.yeilinux.identity.mapper.UserDetailsMapper;
import pe.yeilinux.identity.mapper.UserMapper;
import pe.yeilinux.identity.repository.UserRepository;
import pe.yeilinux.identity.util.SimpleJdbcCallFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource(name= Constant.CURRENTJDBCTEMPLATE)
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SimpleJdbcCallFactory simpleJdbcCallFactory;

    @Override
    public User findByUserName(String userName) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "find_by_user_name",Constant.CURRENTDATABASE)
                                        .returningResultSet("result",new UserMapper());

        Map<String, Object> parameters= new HashMap<>();
        parameters.put("p_username",userName);

        Map<String,Object> result= simpleJdbcCall.execute(parameters);
        List<User> userList = (List<User>) result.get("result");

        if(userList != null && !userList.isEmpty()){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public List<User> findUserByCompanyId(int companyId) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "find_users_by_company_id",Constant.CURRENTDATABASE)
                .returningResultSet("result",new UserMapper());

        Map<String, Object> parameters= new HashMap<>();
        parameters.put("p_company_id",companyId);

        Map<String,Object> result= simpleJdbcCall.execute(parameters);
        List<User> userList = (List<User>) result.get("result");

        if(userList != null && !userList.isEmpty()){
            return userList;
        }
        return null;
    }

    @Override
    public int createPerson(UserRegisterRequest userRegisterRequest) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "create_person",Constant.CURRENTDATABASE);

        Map<String, Object> parameters= new HashMap<>();
        parameters.put("p_first_name",userRegisterRequest.getFirstName());
        parameters.put("p_middle_name",userRegisterRequest.getMiddleName());
        parameters.put("p_last_name",userRegisterRequest.getLastName());
        parameters.put("p_age",userRegisterRequest.getAge());
        parameters.put("p_gender_id",userRegisterRequest.getGenderId());
        parameters.put("p_address",userRegisterRequest.getAddress());

        Map<String, Object> result = simpleJdbcCall.execute(parameters);
        return (int) result.get("p_result");
    }

    @Override
    public void createUser(User userRequest,int typeUserId,int personId,int companyId) {
        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "create_user",Constant.CURRENTDATABASE);

        Map<String, Object> parameters= new HashMap<>();
        parameters.put("p_username",userRequest.getUserName());
        parameters.put("p_phone_number",userRequest.getPhoneNumber());
        parameters.put("p_password",userRequest.getPassword());
        parameters.put("p_type_user_id",typeUserId);
        parameters.put("p_person_id",personId);
        parameters.put("p_company_id",companyId);

        simpleJdbcCall.execute(parameters);
    }

    @Override
    public UserDetails getUserDetails(String username) {

        SimpleJdbcCall simpleJdbcCall = simpleJdbcCallFactory.create(jdbcTemplate, "get_user_details",Constant.CURRENTDATABASE)
                .returningResultSet("result",new UserDetailsMapper());

        Map<String, Object> parameters= new HashMap<>();
        parameters.put("p_username",username);

        Map<String, Object> result = simpleJdbcCall.execute(parameters);
        List<UserDetails> userDetails = (List<UserDetails>) result.get("result");
        if(userDetails != null && !userDetails.isEmpty()){
            return userDetails.get(0);
        }
        return null;
    }
}
