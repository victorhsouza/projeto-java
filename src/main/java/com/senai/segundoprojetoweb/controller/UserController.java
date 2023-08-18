package com.senai.segundoprojetoweb.controller;

import com.senai.segundoprojetoweb.model.UserModel;
import com.senai.segundoprojetoweb.service.CookieService;
import com.senai.segundoprojetoweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    
    @GetMapping
    public String listarUsuarios(Model model, HttpServletRequest request){
        List<UserModel> users = userService.findAll();
        model.addAttribute("users",users);
        model.addAttribute("nome", CookieService.getCookie(request,"nome"));
        return "users/lista-usuarios";
    }

    @GetMapping(value = "/cadastrar")
    public String formCadastrarUsuario(Model model, HttpServletRequest request){
        model.addAttribute("nome", CookieService.getCookie(request,"nome"));
        return "users/register";
    }

    @PostMapping(value = "/cadastrar")
    public String cadastrarUsuario(UserModel userModel){
        userService.saveUser(userModel);
        return "redirect:/usuarios";
    }

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        Optional<UserModel> user = userService.findById(id);
        try{
            model.addAttribute("user",user.get());
            return "users/alterar";
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "users/alterar";

    }

    @PostMapping(value = "/alterar/{id}")
    public String alterarUsuario( @PathVariable Integer id, UserModel userModel){
        Optional<UserModel> user = userService.findById(id);
        if(user.isPresent()){
            userService.saveUser(userModel);
            return "redirect:/usuarios";
        }
        else{
            return "redirect:/usuarios";
        }
    }

    @GetMapping(value = "/excluir/{id}")
    public String deletarUsuario( @PathVariable Integer id){
        userService.deleteUserById(id);
        return "redirect:/usuarios";
    }

    @GetMapping(value = "cadastrar-usuario")
    public String cadastrarUsuarioNaoLogado(){
        return "users/cadastrar-usuario";
    }
}
