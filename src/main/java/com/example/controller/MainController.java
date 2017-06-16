package com.example.controller;

import com.example.model.User;
import com.example.service.AddressService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private UserService userService;
    private AddressService addressService;

    @Autowired
    public MainController(UserService userService, AddressService addressService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("addresses", addressService.getAll());

        return "index";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> submitForm(User user) {
        User persistedUser = userService.saveUser(user);
        System.out.println(userService.getUserById(persistedUser.getId()));

        return new ResponseEntity<>(persistedUser, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/submit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserById(@PathVariable("id") User user) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

}
