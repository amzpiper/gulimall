package com.atguigu.gulimall.product;

public class User {

    String name;
    String age;
    String phone;
    String email;

    //避免外界使用构造器创建User对象
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public static void main(String[] args) {
//        User u = new User().Builder().setAge("age").setPhone("phone").setName("name").setEmail("email").build();
        User user;
        Builder builder = new Builder();
        user = builder.setAge("123").setName("name").setPhone("phone").setEmail("email").build();
        System.out.println(user.toString());
    }
}

class Builder{
    String name;
    String age;
    String phone;
    String email;

    public Builder() {

    }

    public Builder setName(String name) {
        this.name = name;
        return this;
    }

    public Builder setAge(String age) {
        this.age = age;
        return this;
    }

    public Builder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Builder setEmail(String email) {
        this.email = email;
        return this;
    }

    public User build() {
        User user = new User();
        user.name = this.name;
        user.age = this.age;
        user.phone = this.phone;
        user.email = this.email;
        return user;
    }

}