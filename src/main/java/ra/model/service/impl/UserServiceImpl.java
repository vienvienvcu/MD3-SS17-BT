package ra.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.model.entity.User;
import ra.model.repository.IUserRepository;
import ra.model.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;



    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Boolean registerUser(String username, String password, Integer roleId) {
        return userRepository.registerUser(username, password, roleId);
    }
}
