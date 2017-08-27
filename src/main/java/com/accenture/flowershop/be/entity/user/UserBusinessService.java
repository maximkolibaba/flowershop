package com.accenture.flowershop.be.entity.user;

public interface UserBusinessService {
    String login(String user, String password);

    User register(String user, String password, String address);
}
