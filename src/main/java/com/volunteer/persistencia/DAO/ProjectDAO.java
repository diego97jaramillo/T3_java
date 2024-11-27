package com.volunteer.persistencia.DAO;

import com.volunteer.models.Project;
import com.volunteer.persistencia.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProjectDAO {
    public ProjectDAO() {}

    public void addProject(Project objProject) {
        String sql = "INSERT INTO projects (title, description, start_date, end_date, created_By) VALUES (?,?,?,?,?);";
        try(
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
                ) {
            statement.setString(1,objProject.getTitle());
            statement.setString(2,objProject.getDescription());
            statement.setDate(3,objProject.getStart_date());
            statement.setDate(4,objProject.getEnd_date());
            statement.setInt(5, objProject.getCreated_By());


            statement.executeUpdate();
            System.out.println("Se creo el proyecto correctamente");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList <Project> allProjects() {
        ArrayList<Project> projectList = new ArrayList<>();
        String sql = "SELECT * FROM projects;";
        try (
            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql)
                ) {
            while(result.next()) {
                Project newProject = new Project(
                        result.getInt("id"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getDate("start_date").toString(),
                        result.getDate("end_date").toString(),
                        result.getInt("created_by")
                );
                projectList.add(newProject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
}
