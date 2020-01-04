package com.sarath;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutput;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Servlets extends HttpServlet {

    public TaskManager tm;

    public Servlets() throws SQLException {
        tm = new TaskManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,NullPointerException {
        PrintWriter out=resp.getWriter();
        List<Task> List=new ArrayList<>();
        try {

            List=tm.Display();
        } catch (SQLException e) {
            out.println(e);
        } catch (Exception e) {
            out.println(e);
        }
        if(List.isEmpty())
        {
            resp.setStatus(404);
           out.println("Not Found");
        }
        JSONArray js=new JSONArray(List);

        out.println(js);
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        List<Task> List=new ArrayList<>();
        String name =req.getParameter("Name");
        String Id =req.getParameter("Id");
        int Taskid = Integer.parseInt(Id);
        int flag=0;
        try {
           // taskManager = new TaskManager();
            List= tm.searchByName(name);
        }
        catch (SQLException e) {
            out.println(e);
        }
        catch (Exception e) {
            out.println(e);
        }
        for (Task obj : List) {
            if (obj.id == Taskid) {
                try {
                    tm.deletTask(Taskid);
                    out.println("......Deleted.......");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                flag++;
            }
        }
        if(List.isEmpty()||flag==0)
        {
            resp.setStatus(404);
            out.println("Not Found");
        }
        resp.setStatus(202);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse resp) throws  IOException {
        PrintWriter out=resp.getWriter();

        String name = request.getParameter("Name");

        Random random = new Random();
        int id = random.nextInt(100);

        String Description = request.getParameter("Description");
        String Date = request.getParameter("Date");
        try {
            tm.addTask(name,id,Description,Date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println(" "+name+" "+id+" "+Description+" "+Date);
        out.println("--------------Data Added--------------------");


    }
}