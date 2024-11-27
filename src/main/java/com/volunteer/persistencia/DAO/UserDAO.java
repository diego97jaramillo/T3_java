package com.volunteer.persistencia.DAO;

import com.volunteer.models.User;
import com.volunteer.persistencia.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    public UserDAO() {}
    public void addUser(User objUser) {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?,?,?,?);";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
            ) {
            statement.setString(1, objUser.getName());  /// prerapa la declararion de sql
            statement.setString(2, objUser.getEmail());
            statement.setString(3, objUser.getPassword());
            statement.setString(4, objUser.getRole().toString());

            statement.executeUpdate(); /// ejecuta la declaracion SQL
            System.out.println("Se creo correctamente el usuario");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users;";
        try(
            Connection conn = DatabaseConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
                ) {
                while (result.next()) {

                    User user = new User(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("email"),
                            result.getString("password"),
                            result.getString("role").equals("PUBLICANTE") ? User.Role.PUBLICANTE : User.Role.VOLUNTARIO
                    );
                    userList.add(user);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
