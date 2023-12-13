package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class DiceController {
    private Random rnd = new Random();

    @GetMapping("/roll-dice")
    public String showRollDice() {
        return "rolldice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String showRollDice(@PathVariable int guess,
                               Model model) {

        List<String> messages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String message = rollDieAndCreateMessage(guess);
            messages.add(message);
        }

        model.addAttribute("messages", messages);
        return "rolldice";
    }

    private String rollDieAndCreateMessage(int guess) {
        // gen a random number from 1 to 6
        int roll = rnd.nextInt(6); // returns a num from 0 to 5
        roll++; // shift it to a num from 1 to 6

        // put logic for checking the user's guess here
        // don't want thymeleaf in this exercise
        String message = String.format("You guessed %d. The roll is %d. ",
                guess, roll);
        if(guess == roll) {
            message += "You guessed correctly!";
        } else {
            message += "WRONG ANSWER!";
        }

        return message;
    }

}