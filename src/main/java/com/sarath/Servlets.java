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

    public Servlets(){
    }
    TaskManager tm = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,NullPointerException {
        PrintWriter out=resp.getWriter();
        List<Task> List=new ArrayList<Task>();
        try {
            tm = new TaskManager();
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        try {
            String name = req.getParameter("Name");
            String dis = req.getParameter("Description");
            //int Id = req.getParameter("Id");
            Random random = new Random();
            int id = random.nextInt(100);

            String sdueDate = req.getParameter("Due Date in in (DD/MM/YYYY) format");
            SimpleDateFormat sdfo = new SimpleDateFormat("dd/MM/yyyy");
            Date dueDate = null;
            dueDate = sdfo.parse(sdueDate);
            Status st = Status.valueOf("Assigned");
            Date dat = new Date();
            tm.addTask(name,id,dis,sdueDate);
            out.println("..........Added...........");
        }
        catch (NullPointerException e)
        {
            out.println(e);
            out.println("You are not allowed to leave any of the fields empty");
        }
        catch(ParseException p)
        {
            out.println(p);
            out.println("Plz Provide Date in the mentioned format");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}