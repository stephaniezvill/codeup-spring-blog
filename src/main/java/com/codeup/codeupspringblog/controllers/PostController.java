package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.dao.PostRepository;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private Post post1 = new Post(1, "Post 1", "qewrqwerqwerqwe");
    private Post post2 = new Post(2, "Post 2", "qewrqwerqwerqwe");
    private Post post3 = new Post(3, "Post 3", "qewrqwerqwerqwe");

    // Arrays.asList(1, 2, 3)
    private final List<Post> posts = new ArrayList<>(List.of(post1, post2, post3));

    private final PostRepository postDao;

    //Plain English Translation:
    //Every time a PostController get created
    //A postDao should be part of it
    public PostController(PostRepository postDao){
        this.postDao = postDao;
    }

    @GetMapping({"/", ""})
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable long id,
                                Model model) {
        model.addAttribute("post", post1);
        return "/posts/show";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body) {
        Post post = new Post(title, body);
        postDao.save(post);

        return "redirect:/posts";
    }

}