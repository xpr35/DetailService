package com.detailservice.model;

public class Profile {

    private Long ctn;
    private String name;
    private String email;

    public Long getCtn() {
        return ctn;
    }

    public void setCtn(Long ctn) {
        this.ctn = ctn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "ctn=" + ctn +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
