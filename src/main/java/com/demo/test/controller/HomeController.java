package com.demo.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping()
    public String main() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLogin() {
        return "loginForm";
    }


    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public String viewLoginVerify(@RequestParam String id, @RequestParam String pwd, HttpSession session) {
        //model.addAttribute("message", "Hello");
        // TODO : 폼 검증
        Map<String, Object> db = new HashMap<>();
        db.put("id", "hee_su");
        db.put("pwd", "111111");

        if(db.get("id").equals(id) && db.get("pwd").equals(pwd)) {
            session.setAttribute("id", id);
            return "loginSuccess";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout")
    public String viewLogout(HttpSession session) {
        session.invalidate();
        return "loginForm";
    }


}
