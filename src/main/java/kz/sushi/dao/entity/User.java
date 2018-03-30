package kz.sushi.dao.entity;

import java.util.Date;

public class User extends Basic {
    private String login;
    private String email;
    private String address;
    private String phone;
    private Date birthday;
    private int user_role_id;
    private String password;

    public User(String login, String email, String phone, Date birthday, Role role, String password) {
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.user_role_id = user_role_id;
        this.password = password;
    }

    public User() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum Role {
        ADMIN, USER
    }

    @Override
    public String toString() {
        return "User{" +
                "logIn='" + login + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", role=" + user_role_id +
                ", password='" + password + '\'' +
                '}';
    }
}
