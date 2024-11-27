package com.volunteer.controllers;

import com.volunteer.models.User;
import com.volunteer.persistencia.DAO.UserDAO;

import java.util.ArrayList;

public class UserController {
    private UserDAO userDao;

    public UserController() {
        this.userDao = new UserDAO();
    }

    public void addUser(String name, String email, String password, String role) {
        User.Role roleReg = (role.equals("publicante")) ? User.Role.PUBLICANTE : User.Role.VOLUNTARIO;
        User user = new User(1, name, email, password, roleReg);
        this.userDao.addUser(user);
    }

    public ArrayList<User> allUsers() {
        return this.userDao.getAllUsers();
    }

    public boolean exists(int id) {
        ArrayList<User> list = allUsers();

        for(User user: list) {
            if(user.getId() == id) return true;
        }
        return false;
    }

    public User login (String name, String password) {
        ArrayList<User> list = allUsers();

        for(User user: list) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
