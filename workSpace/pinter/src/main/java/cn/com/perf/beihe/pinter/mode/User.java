package cn.com.perf.beihe.pinter.mode;


import java.util.Date;

public class User {
    private Integer id;

    private String userName;

    private String password;

    private Integer age;

    private Integer gender;

    private String phoneNum;

    private String email;

    private String address;

    private Date createTime;

    private Date updateTime;

    public boolean equals(Object o) {
        if (!(o instanceof User))
            return false;
        User u = (User)o;
        return this.userName.equals(u.userName);
    }

    public int hashCode() {
        return this.userName.hashCode();
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "User{id=" + this.id + ", userName='" + this.userName + '\'' + ", age=" + this.age + ", gender='" + this.gender + '\'' + ", phoneNum='" + this.phoneNum + '\'' + ", email='" + this.email + '\'' + ", address='" + this.address + '\'' + '}';
    }
}