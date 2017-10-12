package com.ecovacs.test.common;

/**
 * Created by ecosqa on 17/4/21.
 * save mail information
 */
public class MailInfo {
    private String imapServer;
    private String email;
    private String password;

    public String getImapServer(){
        return imapServer;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setImapServer(String str){
        imapServer = str;
    }

    public void setEmail(String str){
        email = str;
    }

    public void setPassword(String str){
        password = str;
    }
}
