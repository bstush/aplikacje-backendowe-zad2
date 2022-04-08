package com.example.demo.Controllers;

import com.example.demo.Entities.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UsersController {
    private Map<Integer, UserEntity> userList= new HashMap<Integer, UserEntity>(){{
        put(0, new UserEntity("adam", 55));
        put(1, new UserEntity("wiola", 13));
    }};
    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/users")
    @ResponseBody
    public String getUsersList() {
        String usersJson = null;
        try {
            usersJson = objectMapper.writeValueAsString(userList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return usersJson;

    }

    @RequestMapping("/users/{id}/get")
    @ResponseBody
    public String getUser(@PathVariable int id) {
        String userJson = null;

        for(Map.Entry<Integer,UserEntity> user : userList.entrySet()){
            if(user.getKey()==id){
                try {
                    userJson = objectMapper.writeValueAsString(user.getValue());
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                return userJson;
            }
        }

        return "{}";

    }

}
