package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = {"/home", "/index"})
    public ModelAndView get() {
        ModelAndView mav = new ModelAndView("employeesList");
        List<Employee> list = employeeService.get();
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping(value = {"/"})
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        List<Employee> list = employeeService.get();
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping("/openEmployeeView")
    public ModelAndView openEmployeeAddView() {
        ModelAndView mav = new ModelAndView("employeesAdd");
        mav.addObject("employee", new Employee());
        return mav;
    }

    @RequestMapping("/save")
    public ModelAndView save(@ModelAttribute("employee") Employee employeeObj) {
        ModelAndView mav = new ModelAndView("employeesList");
        employeeService.save(employeeObj);
        List<Employee> list = employeeService.get();
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping("/employee/{id}")
    public ModelAndView get(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("employeesAdd");
        Employee employeeObj = employeeService.get(id);
        if (employeeObj == null) {
            throw new RuntimeException("Employee not found" + id);
        }
        mav.addObject("employee", employeeObj);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView("employeesList");
        employeeService.delete(id);
        List<Employee> list = employeeService.get();
        mav.addObject("list", list);
        return mav;
    }
}
