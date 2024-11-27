package com.volunteer;

import com.volunteer.models.User;
import com.volunteer.persistencia.DAO.UserDAO;
import com.volunteer.views.Menu;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Scanner;

import static java.time.LocalDate.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu appMenu = new Menu();
        appMenu.ShowStartingMenu();

        scanner.close();
        /// User usuario1 = new User(1, "pepe", "pepe@gmail.com", "pepe123", User.Role.PUBLICANTE);
    }
}
