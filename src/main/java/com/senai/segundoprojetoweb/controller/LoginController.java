package com.senai.segundoprojetoweb.controller;

import com.senai.segundoprojetoweb.model.UserModel;
import com.senai.segundoprojetoweb.service.CookieService;
import com.senai.segundoprojetoweb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    @GetMapping(value = "/login")
    public String formLogin(){
        return "login/login";
    }

    @PostMapping(value = "/logar")
    public String logar(UserModel userModel, Model model, HttpServletResponse response,String lembrar){
        int tempoCookie;
        UserModel user = loginService.logar(userModel);
        if(user != null){
            if(lembrar != null){
                tempoCookie = 60 * 60 * 24 * 360;
            } else{
               tempoCookie = 60 * 60;
            }
            CookieService.setCookie(response,"usuarioId",String.valueOf(user.getId()),tempoCookie);
            CookieService.setCookie(response,"nome",user.getNome(),tempoCookie);
            return "redirect:/";
        } else{
            model.addAttribute("erro","Usuario e/ou senha invalidos!");
            return "login/login";

        }
    }

    @GetMapping(value="/sair")
    public String logout(HttpServletResponse response){
        CookieService.setCookie(response,"usuarioId","",0);
        return "redirect:/login";
    }
}
