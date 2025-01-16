package api;


import dto.StudebtDTo;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/student")
public class Student extends HttpServlet {

    //make no args constructor
   List<StudebtDTo> list = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     int id = Integer.parseInt(req.getParameter("id"));
     String studentDetails = "";

        for (StudebtDTo studebtDTo : list) {
            if (studebtDTo.getId()==id){
                studentDetails =studebtDTo.toString();
                break;
            }
            
        }

        studentDetails = studentDetails.isEmpty() ? "No student fount": studentDetails;

        resp.getWriter().println(studentDetails);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age =  req.getParameter("age");
        String address = req.getParameter("address");

        int studentId = Integer.parseInt(id);
        int studentAge = Integer.parseInt(age);

        StudebtDTo studebtDTo = new StudebtDTo();

        studebtDTo.setId(studentId);
        studebtDTo.setName(name);
        studebtDTo.setAge(studentAge);
        studebtDTo.setAddress(address);

        boolean isAdded=list.add(studebtDTo);




        resp.getWriter().println("Student  Added"+ isAdded);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String address = req.getParameter("address");

        int studentId = Integer.parseInt(id);
        int studentAge =Integer.parseInt(age);

        boolean isUpdate = false;

        for (StudebtDTo studebtDTo : list) {
            if (studebtDTo.getId()==studentId){
                studebtDTo.setName(name);
                studebtDTo.setAge(studentAge);
                studebtDTo.setAddress(address);
                isUpdate=true;
                break;
            }


        }

        String responseMessage = isUpdate?"Student update successfully" :"Student not fount";
        resp.getWriter().println(responseMessage);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        boolean isUpdate = list.removeIf(studebtDTo -> studebtDTo.getId()==id);

        String responseMessage = isUpdate?"Student is deleted" :" Student not delete";
        resp.getWriter().println(responseMessage);

    }
}

