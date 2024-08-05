package ra.model.entity;


public class User {
    private Integer userId;
    private String userName;
    private String password;
    private Integer roleId;

    public User() {
    }
    public User(Integer userId, String userName, String password, Integer roleId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
