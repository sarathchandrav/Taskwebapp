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
     * @return
     * @throws ParseException when date parameter is not a valida date
     */
    Task addTask(String name, String date, String description, int Id) throws ParseException, SQLException;
    List<Task> Display() throws SQLException, ParseException;
    Task updateTask(String description);
    void deletTask(int deleteId) throws SQLException;
    List<Task> searchById(int Id) throws SQLException;
    List<Task> searchByName(String Name) throws SQLException;
    List<Task> searchAll();
    List<Task> searchByStatus(Status status) throws SQLException;
    Task changeStatus(String name, int Id, Status ModifiedStatus);
    void  sortDate();
    List<Task> getPendingTask();
    List<Task> getTodayTask();

}
