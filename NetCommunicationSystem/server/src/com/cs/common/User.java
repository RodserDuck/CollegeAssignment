package com.cs.common;

import java.io.Serializable;

/**
 *表示一个用户信息
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String passwd;

    public User(String nameId, String passwd) {
        this.userId = nameId;
        this.passwd = passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String nameId) {
        this.userId = nameId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
