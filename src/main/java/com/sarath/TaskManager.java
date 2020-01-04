package com.sarath;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class TaskManager {

    private TaskRepo tr;


    public TaskManager() throws SQLException {

             tr = new DataBaseRepo();
         }




    public void addTask(String name, int id, String description, String date) throws  SQLException {
        try {
            tr.addTask(name, id, description, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Task> Display() throws SQLException, ParseException {
        return tr.Display();
    }

    public void deletTask(int deleteId) throws SQLException {
        tr.deletTask(deleteId);
    }

    public List<Task> searchAll() {
        return tr.searchAll();
    }

    public List<Task> searchByName(String Name) throws SQLException {
        return tr.searchByName(Name);
    }

    public List<Task> searchById(int Id) throws SQLException {
        return tr.searchById(Id);
    }

    public Task changeStatus(String GetName, int GetId, Status ModifiedStatus) {
        return tr.changeStatus(GetName, GetId, ModifiedStatus);
    }

    public List<Task> searchByStatus(Status Status) throws SQLException {
        return tr.searchByStatus(Status);
    }

    public void sortDate(){
        tr.sortDate();
    }

    public List<Task> getPendingTask() throws SQLException {
        return tr.getPendingTask();
    }
    public List<Task> getTodayTask() throws SQLException {
        return tr.getTodayTask();
    }

}
