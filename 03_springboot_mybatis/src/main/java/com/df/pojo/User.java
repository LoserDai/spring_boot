package com.df.pojo;

import java.util.Date;

/**
 * @author Loser
 * @date 2021年11月08日 12:01
 */
public class User {
    private Integer id;

    private String nam;

    private String pwd;

    private Integer sex;

    private Date birth;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nam='" + nam + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
