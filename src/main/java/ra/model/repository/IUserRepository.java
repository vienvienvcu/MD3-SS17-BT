package ra.model.repository;

import ra.model.entity.User;

public interface IUserRepository {
    User findByUserName(String userName);
    Boolean registerUser( String username,String password, Integer roleId);
}
