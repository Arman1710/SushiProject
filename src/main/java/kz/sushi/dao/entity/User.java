package kz.sushi.dao.entity;

import java.util.Date;

public class User extends Basic {
    private String login;
    private String email;
    private String address;
    private String phone;
    private Date birthday;
    private Role role;
    private String password;

    public User(String login, String email, String phone, Date birthday, Role role, String password) {
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public Role setRole(Role role) {
        this.role = role;
        return role;
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
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
