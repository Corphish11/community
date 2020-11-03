package com.nowcoder.community.Controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello World";
    }

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + " " + value);
        }
        System.out.println(request.getParameter("code"));

        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter()
        ){
            writer.write("<h1>hello<h1/>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path =  "/student", method = RequestMethod.GET)
    @ResponseBody
    public void getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);

    }

    @RequestMapping(path = "/student/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){

        return "a student";
    }

    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, String age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "tim");
        mav.addObject("age", 15);
        mav.setViewName("demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){

        model.addAttribute("name", "DUT");
        model.addAttribute("age", 80);
        return "/demo/view";
    }

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp= new HashMap<>();
        emp.put("name","tim");
        emp.put("age",23);
        emp.put("salary", 8000.00);
        return emp;
    }
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> empList = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","tim");
        emp.put("age",23);
        emp.put("salary", 8000.00);
        empList.add(emp);
        emp = new HashMap<>();
        emp.put("name","cook");
        emp.put("age",24);
        emp.put("salary", 9000.00);
        empList.add(emp);
        return empList;
    }
}
