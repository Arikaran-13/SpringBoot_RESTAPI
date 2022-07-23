package com.arikaran.serviceImpl;

import com.arikaran.Dto.UserDto;
import com.arikaran.entity.User;
import com.arikaran.repository.UserRepository;
import com.arikaran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repo;
    @Override
    public UserDto saveUser(User user) {

      return convertEntityToDto(user);

    }

    @Override
    public List<UserDto> getAll() {

        return repo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(int id) {

       Optional<User> op= repo.findById(id);
       if(op.isEmpty()){
           throw new RuntimeException("User not found for the id provided");
       }
       else{
           return convertEntityToDto(op.get());
       }
    }

    @Override
    public UserDto update(User user) {
         Optional<User>op=    repo.findById(user.getId());
         if(op.isEmpty()){
             throw new RuntimeException("User not found for the id");
         }

         return convertEntityToDto(user);
    }

    @Override
    public void DeleteById(int id) {
        Optional<User> op =repo.findById(id);
        if(op.isEmpty()){
            throw new RuntimeException("User not found for the given id");
        }
        else
        repo.deleteById(id);
    }

    private UserDto convertEntityToDto(User user){

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLocation(user.getLocation());
        dto.setName(user.getName());
        return dto;
    }
}
