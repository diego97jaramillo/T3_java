package com.volunteer.persistencia.DAO;

import com.volunteer.models.Inscription;
import com.volunteer.models.Project;
import com.volunteer.models.User;
import com.volunteer.persistencia.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InscriptionDAO {

    public InscriptionDAO() {}

    public void add(Inscription inscription) {
        String sql = "INSERT INTO inscriptions (user_id, project_id, date) VALUES (?,?,?);";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, inscription.getUserId());
            statement.setInt(2, inscription.getProjectId());
            statement.setDate(3, inscription.getDate());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Project> listarProjectosInscrito(int id) {
        ArrayList<Project> lista = new ArrayList<>();
        String sql = "SELECT *, inscriptions.user_id as userID, projects.id as idProject FROM inscriptions RIGHT JOIN projects ON projects.id = inscriptions.project_id where inscriptions.user_id = ?;";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Project project = new Project(
                        result.getInt("idProject"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getDate("start_date").toString(),
                        result.getDate("end_date").toString(),
                        result.getInt("userID")
                );
                lista.add(project);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void inscritosXproyecto(int id) {
        ArrayList<Object> lista = new ArrayList<>();
        String sql = "SELECT *, users.role as role, inscriptions.user_id as inscritoId, users.name as nombre, users.email as email, users.password as passwd,  projects.id as idProject, projects.created_by as createdBy FROM inscriptions INNER JOIN users ON inscriptions.user_id = users.id INNER JOIN projects ON inscriptions.project_id = projects.id WHERE created_by like ?;";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User user = new User(
                        result.getInt("inscritoId"),
                        result.getString("nombre"),
                        result.getString("email"),
                        result.getString("passwd"),
                        result.getString("role").equals("PUBLICANTE") ? User.Role.PUBLICANTE : User.Role.VOLUNTARIO
                );

                Project project = new Project(
                        result.getInt("idProject"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getDate("start_date").toString(),
                        result.getDate("end_date").toString(),
                        result.getInt("createdBy")
                );
                System.out.println("Project Name: "+ project.getTitle() + ", Subscribed: " + user.getName());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
