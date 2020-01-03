package com.sarath;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRepo implements TaskRepo {

    List<Task> taskList;
    public FileSystemRepo()   {
        taskList = readFromFile();
    }


    public static final String TaskFile_path = "/home/sarathv/Desktop/FileSystem/FileData.json";

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public ObjectMapper objectMapper = new ObjectMapper();
    final File file = new File(TaskFile_path);

    public void writeToFile(List<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new FileWriter(TaskFile_path), tasks);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Task> readFromFile() {
        final File file = new File(TaskFile_path);
        if (file.exists()) {
            try {
                return objectMapper.readValue(file, TaskList.class);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public Task addTask(String name, String date, String description, int Id) throws ParseException {
           // taskList = readFromFile();

            Task To = new Task();
            To.setName(name);
            To.setDescription(description);
            To.setId(Id);
            To.setDate(format.parse(date));
            taskList.add(To);
            writeToFile(taskList);
            return To;

    }

    @Override
    public List<Task> Display() {
        //taskList = readFromFile();
        return taskList;
    }

    @Override
    public Task updateTask(String description) {
        return null;
    }

    @Override
    public void deletTask(int deleteId)  {
        List<Task> DeleteList = new ArrayList<Task>();
        taskList = readFromFile();
        for(int i=0;i<taskList.size();i++){
            Task object = taskList.get(i);
            if(object.id == deleteId)
            {
                System.out.println("removing"+object);
                taskList.remove(i);
            }
        }
        writeToFile(taskList);

    }

    @Override
    public List<Task> searchById(int Id) {
        List<Task> IdSearchList = new ArrayList<>();
        for (Task obj : taskList) {
            if (obj.id == Id)
                IdSearchList.add(obj);
        }
        return IdSearchList;
    }

    @Override
    public List<Task> searchByName(String Name) {
        List<Task> nameSearchList = new ArrayList<>();
        for (Task obj : taskList) {
            if (obj.name.equals(Name))
                nameSearchList.add(obj);
        }
        return nameSearchList;
    }

    @Override
    public List<Task> searchAll() {
        return null;
    }

    @Override
    public List<Task> searchByStatus(Status status) {
        List<Task> statusSearchList = new ArrayList<>();
        for (Task obj : taskList) {
            if (obj.status.equals(status))
                statusSearchList.add(obj);
        }
        return statusSearchList;
    }

    @Override
    public Task changeStatus(String name, int Id, Status ModifiedStatus) {
        //taskList = readFromFile();
        for(int i=0;i<taskList.size();i++){
            Task object = taskList.get(i);
            if(object.id == Id)
            {
                System.out.println("Modifing"+" "+object.status+" " +"to"+" "+ModifiedStatus);
                object.status = ModifiedStatus;
                System.out.println(object.status);
            }
        }
        writeToFile(taskList);
        return null;
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
