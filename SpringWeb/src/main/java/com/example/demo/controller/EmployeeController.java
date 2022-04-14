package com.example.demo.controller;


import com.example.*;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(path = "/addEmployee")
    public String addEmployee(Model m){
        Employee e=new Employee();
        m.addAttribute("emp",e);
        return "add_Employee";
    }
    @RequestMapping(path = "/")
    public String show_home_Page(Model model){
        List<Employee> employees=service.getEmployees();
        model.addAttribute("e1",employees);
        return "index";
    }
    @RequestMapping(path = "/save",method = RequestMethod.GET)
    public String insert_Employee_Controller(@ModelAttribute("emp") Employee e){
        service.insert(e);
        return "redirect:/";
    }
    @RequestMapping(path = "edit/{id}")
    public ModelAndView edit_Controller(@PathVariable(name = "id") int id){
        ModelAndView modelAndView=new ModelAndView("edit_Employee");
        Employee e=service.getEmployee(id);
        modelAndView.addObject("emp",e);
        return modelAndView;
    }
    @RequestMapping(path = "delete/{id}")
    public String delete_Controller(@PathVariable(name = "id") int id){
        service.delete(id);
        return "redirect:/";
    }
    @RequestMapping(path = "sort")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String show_By_Sort(Model model) {
    	List<Employee> employees=service.fetchsort();
    	model.addAttribute("e2", employees);
    	return "index2";
    }
    
    @RequestMapping(path = "page")
    public String show_By_Page(Model model) {
    	int pageSize=5,off=0;
    	Page<Employee> page=service.fetchPagination(off, pageSize);
    	List<Employee> employees=page.getContent();
    	model.addAttribute("e3", employees);
    	return "index3";
    	
    	
    }


}
