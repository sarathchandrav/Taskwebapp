package com.sarath;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface TaskRepo {
    /**
     *
     * @param name
     * @param date
     * @param description
     * @param Id

     * @throws ParseException when date parameter is not a valida date
     */
    void addTask(String name, int Id, String description, String date) throws ParseException, SQLException;
    List<Task> Display() throws SQLException, ParseException;
    Task updateTask(String description);
    void deletTask(int deleteId) throws SQLException;
    List<Task> searchById(int Id) throws SQLException;
    List<Task> searchByName(String Name) throws SQLException;
    List<Task> searchAll();
    List<Task> searchByStatus(Status status) throws SQLException;
    Task changeStatus(String name, int Id, Status ModifiedStatus);
    void  sortDate();
    List<Task> getPendingTask() throws SQLException;
    List<Task> getTodayTask() throws SQLException;

}
