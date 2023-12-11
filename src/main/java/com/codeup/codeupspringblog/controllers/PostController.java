package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {
    @GetMapping ("/posts")
    @ResponseBody
    public String getAllPosts() {
        return "This is the posts index page";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getAllPosts(@PathVariable Long id) {
        return "This is the view for post with id: " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String getCreatePosts() {
        return "This is the form for creating a post";
    }

    @PostMapping("/create")
    @ResponseBody
    public String submitPosts() {
        return "This is the action for creating a new post with data: ";
    }
}
