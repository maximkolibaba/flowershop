package com.accenture.flowershop.be.access.user;

//import com.accenture.flowershop.be.access.DAO;
import com.accenture.flowershop.be.entity.user.User;

import java.util.List;
public interface UserDAO  {
//public interface UserDAO extends DAO<User> {
//    List<User> getAll();
    User getByLogin(String login);
    boolean create(User user);
    User update(User user);
    //
//    List<User> getByName(String firstName, String lastName);
//    User getByLogin(String login);
//    User getByEmail(String email);
//    User getByPhoneNumber(String phoneNumber);
}