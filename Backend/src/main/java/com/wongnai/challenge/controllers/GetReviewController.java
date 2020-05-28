package com.wongnai.challenge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetReviewController {

    @RequestMapping(value = "/reviews/{id}", produces = "application/json")
    @ResponseBody
    public String getReview(@PathVariable("id") Long id) {
            return String.format("Hello %s!", id);
        }

}
