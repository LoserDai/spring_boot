package com.df.pojo;

/**
 * @author Loser
 * @date 2021年11月05日 10:55
 */
public class Cat {
    private String catName;
    private Integer age;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "catName='" + catName + '\'' +
                ", age=" + age +
                '}';
    }
}
