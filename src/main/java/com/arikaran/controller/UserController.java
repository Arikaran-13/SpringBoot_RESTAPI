package com.arikaran.controller;

import com.arikaran.Dto.UserDto;
import com.arikaran.entity.User;
import com.arikaran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/hello")
    public String hello() {


        return "hello world";
    }
    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto user ){

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
    @GetMapping("/displayAll")
    public ResponseEntity<List<UserDto>> getAll(){

        return new ResponseEntity<>( service.getAll(),HttpStatus.OK);

    }
    @PutMapping("/update")
    public ResponseEntity<UserDto> update(User user){
        try {
            return new ResponseEntity<>(service.update(user),HttpStatus.OK);
        }catch(Exception e){
          throw new RuntimeException("Cannot update");
        }

    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id")int id){
        try{
            service.DeleteById(id);
            System.out.println("Deleted");

        }catch(Exception e){
            throw new RuntimeException("Id not found in the db");
        }

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto>getById(@PathVariable("id")int id){
        try{
          return new  ResponseEntity<>( service.getById(id),HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException("Id not found pls check");
        }
    }

}
