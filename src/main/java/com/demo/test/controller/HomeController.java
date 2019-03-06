package com.demo.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "/")
    public String main() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLogin() {
        return "loginForm";
    }


    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    public Object viewLoginVerify(@ModelAttribute() @Valid User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        //model.addAttribute("message", "Hello");
        // TODO : 폼 검증
        User db = new User();
        db.setId("hee_su");
        db.setPwd("111111");
//        if (result.hasErrors()) {
//            List<ObjectError> list = result.getAllErrors();
//            for (ObjectError e : list) {
//            }
//        }

        if(db.getId().equals(user.getId()) && db.getPwd().equals(user.getPwd())){
            session.setAttribute("id", user.getId());
            redirectAttributes.addFlashAttribute("id", user.getId());
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value="/logout")
    public String viewLogout(HttpSession session) {
        //System.out.println(session.getAttribute("id"));
        session.invalidate();
        return "redirect:/";
    }


}
