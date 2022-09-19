package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
//        System.out.println("In display Search Resulsts: " + searchType + " " + searchTerm);
//        model.addAttribute()
        model.addAttribute("columns", columnChoices);
        searchTerm = searchTerm.toLowerCase().trim();
        ArrayList<Job> searchResult;
        if (searchTerm.equals("all") || searchTerm.equals("")) {
            model.addAttribute("success", "Success");
            searchResult = JobData.findAll();
        } else {
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchTerm", searchTerm);
            searchResult = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", searchResult);
        return "search";
    }


}
