package ra.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.model.entity.User;
import ra.model.repository.IUserRepository;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Override
    public User findByUserName(String userName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        User userInfo = null;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call findByUserName(?)}");
            callableStatement.setString(1, userName);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                userInfo = new User();
                userInfo.setUserId(resultSet.getInt("userId"));
                userInfo.setUserName(resultSet.getString("username"));
                userInfo.setPassword(resultSet.getString("password"));
                userInfo.setRoleId(resultSet.getInt("roleId"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }

        return userInfo;
    }

    @Override
    public Boolean registerUser(String username, String password, Integer roleId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        Boolean result = false;
        try {
            connection = ConnectionDB.openConnection();
            callableStatement = connection.prepareCall("{call registerUser(?,?,?)}");
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.setInt(3, roleId);
            callableStatement.execute();
            result = true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(connection, callableStatement);
        }
        return result;
    }
}
