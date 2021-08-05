package com.middleware.provider.domain;

/**
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
public class HelloWorld {

    private String name;

    private String age;

    private String motto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    @Override
    public String toString() {
        return "Age: " + age + ", Name: "+ name + ", Motto: " + motto;
    }
}
