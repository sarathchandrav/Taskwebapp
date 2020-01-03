package com.sarath;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepo extends FileSystemRepo  implements TaskRepo  {

    public List<Task> taskList = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Task addTask(String name, String date, String description, int Id) throws ParseException {
        /*Task To = new Task();
        To.setName(name);
        To.setDescription(description);
        To.setId(Id);
        To.setDate(format.parse(date));
        //
        taskList.add(To);*/

        return super.addTask(name,  date,  description,  Id);
    }

    @Override
    public List<Task> Display() {
        return super.Display();
        //return taskList;
    }

    @Override
    public Task updateTask(String description) {
        return null;
    }

    @Override
    public void deletTask(int deleteId) {
        super.deletTask(deleteId);
    }

    @Override
    public List<Task> searchById(int Id) {
        return super.searchById(Id);
    }

    @Override
    public List<Task> searchByName(String Name) {
        return super.searchByName(Name);
    }

    @Override
    public List<Task> searchAll() {
        return super.Display();
    }

    @Override
    public List<Task> searchByStatus(Status status) {
        return super.searchByStatus(status);
    }

    @Override
    public Task changeStatus(String name, int Id, Status status) {
        return super.changeStatus(name,Id,status);
    }

    @Override
    public void sortDate() {

    }

    @Override
    public List<Task> getPendingTask() {
        return null;
    }

    @Override
    public List<Task> getTodayTask() {
        return null;
    }
}
