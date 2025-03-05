package com.example.cricket.controller;


import com.example.cricket.service.DeleteAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/DeleteAll")
public class DeleteAllController {

    private final DeleteAllService deleteAllService;

    @PostMapping("/update")
    public String updation(){
      return  deleteAllService.deleteAll();
    }
}
