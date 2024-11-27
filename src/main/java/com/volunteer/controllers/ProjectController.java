package com.volunteer.controllers;

import com.volunteer.models.Project;
import com.volunteer.persistencia.DAO.ProjectDAO;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ProjectController {
    private UserController userController;
    private ProjectDAO projectDAO;
    public ProjectController(){
        this.projectDAO = new ProjectDAO();
        this.userController = new UserController();
    }

    public void addProject() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("escribe el titulo: ");
        String newTitle = scanner.nextLine();
        System.out.println("escribe la description: ");
        String newDescription = scanner.nextLine();
        System.out.println("escribe la fecha de inicio: ");
        String newStart_date = scanner.next();
        System.out.println("escribe la fecha de finalizacion: ");
        String newEnd_date = scanner.next();
        System.out.println("escribe el numero de id del creador: ");
        int newCreated_By = scanner.nextInt();
        if (userController.exists(newCreated_By)) {
            Project newproject = new Project(0,newTitle, newDescription, newStart_date, newEnd_date, newCreated_By);
            projectDAO.addProject(newproject);
        } else {
            System.out.println("No existe ese id de usuario");
        }
    }

    public void listProjects() {
        projectDAO.allProjects().forEach(project -> System.out.println(project.toString()));
    }
}
