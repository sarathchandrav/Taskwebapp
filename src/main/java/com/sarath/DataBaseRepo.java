package com.sarath;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
public class DataBaseRepo  implements TaskRepo {

    Connection connection;
    Statement statement;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    DataBaseRepo() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "testuser", "password");
            statement = connection.createStatement();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e);
        }

    }


    @Override
    public void addTask(String name, int Id , String description, String date) throws SQLException {


            Status status = Status.valueOf("Initial");
           String query = "insert into TaskTable values('"+name+"','"+description+"','"+Id+"','"+date+"','"+status+"')";
               statement.executeUpdate(query);

    }

    @Override
    public List<Task> Display() throws SQLException {


            String query = "select * from TaskTable ";
            ResultSet searchList = statement.executeQuery(query);

            List<Task> DisplayList = new ArrayList<Task>();
            while (searchList.next()) {

                String name = searchList.getString(1);
                String description = searchList.getString(2);
                int id = searchList.getInt(3);
                Date date = searchList.getDate(4);
                String stat = searchList.getString(5);
                Status status;
                status= Status.valueOf(stat);

                Task To = new Task();
                To.setName(name);
                To.setDescription(description);
                To.setId(id);
                To.setDate((date));
                To.setStatus(status);
                DisplayList.add(To);


            }

        return DisplayList;


    }

    @Override
    public Task updateTask(String description) {
        return null;
    }

    @Override
    public void deletTask(int deleteId) throws SQLException {

       String query = "delete from TaskTable where Id ="+deleteId+";";
       statement.executeUpdate(query);
    }

    @Override
    public List<Task> searchById(int Id) throws SQLException {
        List<Task> IdSearchList = new ArrayList<Task>();
        ResultSet searchList = null;
        try {
            String query = "select * from TaskTable where Id like "+Id+";";
            searchList = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GenerateList(searchList);
    }

    @Override
    public List<Task> searchByName(String Name) throws SQLException {
        List<Task> nameSearchList = new ArrayList<Task>();
        ResultSet searchList = null;
        try {
            String query = "select * from TaskTable where name  like'"+Name+"';";
            searchList = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GenerateList(searchList);
    }

    @Override
    public List<Task> searchAll() {
        return null;
    }

    @Override
    public List<Task> searchByStatus(Status status) throws SQLException {

        List<Task> nameSearchList = new ArrayList<Task>();
        ResultSet searchList = null;
        try {
            String query = "select * from TaskTable where status like '"+status+"';";
            searchList = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GenerateList(searchList);
    }

    @Override
    public Task changeStatus(String name, int Id, Status status) {
        String query = "UPDATE TaskTable SET status= '"+status+"' WHERE Id="+Id+";";
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sortDate() {
        String query = "SELECT * FROM TaskTable ORDER BY date DESC; ";
        try {
            ResultSet searchList = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Task> getPendingTask() {
        return null;
    }

    @Override
    public List<Task> getTodayTask() {
        return null;
    }

    public List<Task> GenerateList(ResultSet Rresultset) throws SQLException {



         List<Task> DisplayList = new ArrayList<Task>();
         while (Rresultset.next()) {

             String name = Rresultset.getString(1);
             String description = Rresultset.getString(2);
             int id = Rresultset.getInt(3);
             Date date = Rresultset.getDate(4);
             String stat = Rresultset.getString(5);
             Status status;
             status= Status.valueOf(stat);

             Task To = new Task();
             To.setName(name);
             To.setDescription(description);
             To.setId(id);
             To.setDate((date));
             To.setStatus(status);
             DisplayList.add(To);


         }
         return DisplayList;
     }
}
