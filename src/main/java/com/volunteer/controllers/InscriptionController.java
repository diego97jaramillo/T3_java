package com.volunteer.controllers;

import com.volunteer.models.Inscription;
import com.volunteer.persistencia.DAO.InscriptionDAO;

import java.sql.Date;
import java.util.Scanner;

public class InscriptionController {
    private ProjectController projectController = new ProjectController();
    private InscriptionDAO inscriptionDAO = new InscriptionDAO();
    public InscriptionController() {
    }

    public void addInscription(int id) {
        Scanner scanner = new Scanner(System.in);
        projectController.listProjects();
        System.out.println("Escribe el id del evento al que quieres participar");
        int projectId = scanner.nextInt();
        scanner.nextLine();
        String date = new Date(System.currentTimeMillis()).toString();
        Inscription inscription = new Inscription(0, id, projectId, date);
        inscriptionDAO.add(inscription);
    }

    public void projectosInscrito(int id){
        inscriptionDAO.listarProjectosInscrito(id).forEach(project -> System.out.println(project.toString()));
    }

    public void listaInscritosXProyecto(int id) {
        inscriptionDAO.inscritosXproyecto(id);
    }
}
