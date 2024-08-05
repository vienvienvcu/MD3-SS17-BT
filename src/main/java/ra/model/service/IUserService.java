package ra.model.service;

import ra.model.entity.User;

public interface IUserService {
    User findByUserName(String userName);
    Boolean registerUser( String username,String password, Integer roleId);
}
