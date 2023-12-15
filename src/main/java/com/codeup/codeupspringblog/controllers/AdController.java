package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.dao.AdDao;
import com.codeup.codeupspringblog.dao.UserRepository;
import com.codeup.codeupspringblog.models.Ad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/ads")
public class AdController {

    private AdDao adDao;
    private UserRepository userDao;

//    public AdController(AdDao adDao){
//        this.adDao = adDao;
//    }

    @GetMapping(value = {"", "/"})
    public String adIndex(Model model){
        List<Ad> ads = adDao.findAll();
        model.addAttribute("ads", ads);
        return "/ads/index";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String showAd(@PathVariable long id,
                         Model model) {
        Ad ad;
        if (adDao.findById(id).isPresent()){
            ad = adDao.findById(id).get();
        } else {
            ad = new Ad("Ad not found", "");
        }
        model.addAttribute("ad", ad);
        return "/ads/show";
    }

    @GetMapping({"/create", "/create/"})
    public String showCreate() {
        return "/ads/create";
    }

    @PostMapping({"/create", "/create/"})
    public String doCreate(@RequestParam(name="title") String title,
                           @RequestParam(name = "description") String description) {
        Ad ad = new Ad(title, description);
        ad.setUser(userDao.findUserById(1L));
        adDao.save(ad);
        return "redirect:/ads";
    }
}