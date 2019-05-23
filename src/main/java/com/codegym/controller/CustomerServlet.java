package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action1 = req.getParameter("action");
        if (action1 == null) {
            action1 = "";
        }
        switch (action1) {
            case "create":
                createCustomer(req,resp);
                break;
            case "edit":

                break;
            case "delete":
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(req,resp);
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "view":
                break;
            default:
                listCustomers(req,resp);
                break;
        }
    }

    private void listCustomers(HttpServletRequest req, HttpServletResponse rep) {
        List<Customer> customers = this.customerService.findAll();
        req.setAttribute("customers", customers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("web/customer/list.jsp");
        try {
            dispatcher.forward(req, rep);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm (HttpServletRequest req, HttpServletResponse rep) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("web/customer/create.jsp");
        try {
            dispatcher.forward(req, rep);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    private void createCustomer (HttpServletRequest req, HttpServletResponse rep){
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int id = (int) (Math.random() * 1000);

        Customer customer = new Customer(id, name, email, address);
        this.customerService.save(customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("web/customer/create.jsp");
        req.setAttribute("message", "New customer was created");

        try {
            dispatcher.forward(req, rep);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
