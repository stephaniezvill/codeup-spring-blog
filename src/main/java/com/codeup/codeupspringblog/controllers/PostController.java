package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.dao.PostRepository;
import com.codeup.codeupspringblog.dao.UserRepository;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
  private final PostRepository postDao;
  private final UserRepository userDao;

    //Plain English Translation:
    //Every time a PostController get created
    //A postDao should be part of it
    public PostController(PostRepository postDao, UserRepository userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping({"/", ""})
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "/posts/index";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String getPostDetail(@PathVariable long id,
                                Model model) {
        Post post;
        if (postDao.findById(id).isPresent()){
            post = postDao.findById(id).get();
        } else {
            post = new Post("Post not found", "");
        }
        model.addAttribute("post", post);
        return "/posts/show";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(@ModelAttribute Post post) { User user = userDao.findUserById(1L);
        post.setUser(user);
        postDao.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable long id,
                               Model model) {
        Post post;
        if (postDao.findById(id).isPresent()) {
            post = postDao.findById(id).get();
        } else {
            post = null;
        }
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String editAd(@ModelAttribute Post modifiedPost){
        // validate the incoming posts changes?
        if(modifiedPost.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be blank!");
        }

        // go grab the post's user from the already saved post in the db
        // and set that user's id in the modified post
        Post oldPost = null;
        Optional<Post> checkPost = postDao.findById(modifiedPost.getId());
        if(checkPost.isPresent()) {
            oldPost = checkPost.get();
        }
        if(oldPost != null) {
            modifiedPost.setUser(oldPost.getUser());
        }

        postDao.save(modifiedPost);
        return "redirect:/posts";
    }

}