package com.demo.test.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Component
public class User {
    @NotEmpty
    @Size(min=3, message = "Your username is probably longer than this. can you double check? 0_0")
    @Pattern(regexp="^[a-zA-Z0-9\\_]+$", message = "use letters, numbers or '_'. (e.g. arya_starks) 0_0")
    private String id;

    @NotEmpty
    @Size(min=6, message = "Type 6 characters or more! 0_0")
    private String pwd;

    public User() { }

    public void setId(String id) {
        this.id = id;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getId() {
        return id;
    }
    public String getPwd() {
        return pwd;
    }
}
