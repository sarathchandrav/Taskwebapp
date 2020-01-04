package com.sarath;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    static TaskManager tm;

    public static void RepositoryType() throws SQLException,NullPointerException {

        tm = new TaskManager();
    }

    public static void main(String[] args) throws ParseException, SQLException {

        while (true) {
            System.out.println(" 1)Add\n 2)Display Data\n 3)Delete\n 4)Change Status\n 5)Search By Status\n 6)Sort Date\n 7)Get Pending Tasks\n 8)Get Today Tasks\n 9)Quit ");

            Scanner sc = new Scanner(System.in);
            int selectTask = sc.nextInt();

            RepositoryType();
            if (selectTask == 1) {
                System.out.println("Enter the task name");
                String name = sc.next();
                if (name.equals(" ")) {
                    System.out.println("Enter the task name");
                    name = sc.nextLine();
                }
                System.out.println("Enter Description");
                String description = sc.next();

                Random random = new Random();
                int id = random.nextInt(100);

                System.out.println("Enter Date as yyyy-mm-dd");
                String date = sc.next();

                tm.addTask(name, id, description, date);
                System.out.println("name:"+name);

            } else if (selectTask == 2) {
                System.out.println("Displaying all tasks.......");
                List<Task> taskList = tm.Display();
                if(taskList.isEmpty())
                {
                    System.out.println("Not Found");
                }
                else {
                    System.out.println(".............Data...............");
                    Print(taskList);
                }


            } else if (selectTask == 3) {
                try {
                    System.out.println("Please Enter the name: ");
                    String deleteName = sc.next();
                    Print(tm.searchByName(deleteName));
                    //tm.searchByName(deleteName);
                    System.out.println("Enter the Id:");
                    int deleteId = sc.nextInt();
                    List<Task> IdSearchList = tm.searchById(deleteId);
                    for (Task object : IdSearchList) {
                        if (object.id == (deleteId)) {
                            tm.deletTask(object.id);
                        }
                    }
                }
                catch (InputMismatchException e)
                {
                    System.out.println("-------------------------\n------Data not found-----\n-------------------------");
                }


            } else if (selectTask == 4) {

                System.out.println("Please Enter the name: ");
                String GetName = sc.next();
                Print(tm.searchByName(GetName));
                System.out.println("Enter the Id:");
                int GetId = sc.nextInt();

                System.out.println("Enther The Modified Status:(Initial,Inprocess,Done)");
                String ModifiedStatus = sc.next();
                Status status;
                try{
                    status= Status.valueOf(ModifiedStatus);
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println(e);
                    System.out.println("Plz type only from the above given options only, try again");
                    break;
                }

                 tm.changeStatus(GetName,GetId,status);

            } else if (selectTask == 5) {
                System.out.println("Enther The Status:(Initial,Inprocess,Done)");
                String GetStatus = sc.next();
                Status status;
                try{
                    status= Status.valueOf(GetStatus);
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println(e);
                    System.out.println("Plz type only from the above given options only, try again");
                    break;
                }
                Print(tm.searchByStatus(status));


            } else if (selectTask == 6) {
                tm.sortDate();


            } else if (selectTask == 7) {
               Print(tm.getPendingTask());

            } else if (selectTask == 8) {
                Print(tm.getTodayTask());

            } else if (selectTask == 9) {
                exit(1);
            }

        }
    }

    static void Print(List<Task> list) {
        for (Task obj : list) {
            System.out.println("Id:" + obj.id);
            System.out.println("Name:" + obj.name);
            System.out.println("Description:" + obj.description);
            System.out.println("Due date:" + obj.date);
            System.out.println("Status:" + obj.status);
            System.out.println();
        }
    }
}


