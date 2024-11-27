package com.volunteer.views;

import com.volunteer.controllers.InscriptionController;
import com.volunteer.controllers.ProjectController;
import com.volunteer.controllers.UserController;

import java.text.BreakIterator;
import java.util.Scanner;

public class Menu {
    String option;
    String userType;
    UserController userController = new UserController();
    ProjectController projectController = new ProjectController();
    InscriptionController inscriptionController = new InscriptionController();


    public void ShowStartingMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                  Optiones:
                  1) iniciar sesion.\s
                  2) Registrar
                \s""");
        option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.println("Escribe el nombre: ");
                String name = scanner.next();
                System.out.println("Escribe la contraseña: ");
                String password = scanner.next();

                if (userController.login(name, password) != null) {
                    this.userType = userController.login(name, password).getRole().name();
                    int userId = userController.login(name, password).getId();
                    actionsMenu(this.userType, userId);
                }
                break;
            case "2":
                System.out.println("Escribe el nombre: ");
                String nameRegister = scanner.next();
                System.out.println("Escribe la contraseña: ");
                String passwordRegister = scanner.next();
                System.out.println("Escribe el email: ");
                String emailRegister = scanner.next();
                System.out.println("Escribe la role: ");
                String roleRegister = scanner.next().toLowerCase();
                userController.addUser(nameRegister, emailRegister, passwordRegister, roleRegister);
                System.out.println("Se ha registrado el usuario");
        }


        scanner.close();
    }

    public void actionsMenu(String type, int userId) {
        Scanner scanner = new Scanner(System.in);
        boolean activo = true;
        String actionOption;
        if (type.equals("PUBLICANTE")) {
            while(activo) {
                System.out.println("""
                        1)Crear un proyecto de voluntariado.
                        2)Ver la lista de proyectos que han creado.
                        3)Ver la lista de voluntarios inscritos en cada uno de sus proyectos.
                        4)Salir del programa\s
                        \s
                        """);
                actionOption = scanner.next();

                switch (actionOption) {
                    case "1":
                        System.out.println("crear proyecto");
                        projectController.addProject();
                        break;
                    case "2":
                        projectController.listProjects();
                        break;
                    case "3":
                        System.out.println("ver lista de voluntarios inscritos en cada uno de sus proyectos");
                        inscriptionController.listaInscritosXProyecto(userId);
                        break;
                    case "4":
                        activo = false;
                        break;
                }
            }
        }

        while (activo) {
            System.out.println("""
                    \n
                    Qué opción vas a escoger \n
                    1)Ver la lista de proyectos disponibles.
                    2)Inscribirse en un proyecto.
                    3)Ver en qué proyectos está inscrito.
                    4)Salir del programa\s
                    \s""");
            actionOption = scanner.next();

            switch (actionOption) {
                case "1":
                    projectController.listProjects();
                    break;
                case "2":
                    System.out.println("Inscribirse en un proyecto.");
                    inscriptionController.addInscription(userId);
                    break;
                case "3":
                    System.out.println("los proyecto que esta inscrito");
                    inscriptionController.projectosInscrito(userId);
                    break;
                case "4":
                    activo = !activo;
                    break;
            }
        }
        scanner.close();
    }
}
