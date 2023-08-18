package com.senai.segundoprojetoweb.service;

import com.senai.segundoprojetoweb.model.UserModel;
import com.senai.segundoprojetoweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel saveUser(UserModel userModel){
        return userRepository.save(userModel);
    }

    public Optional<UserModel> findById(Integer id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }


}
