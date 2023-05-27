package day23.beer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import day23.beer.model.Brewery;
import day23.beer.repository.StyleRepo;

@Controller
public class StyleController {
    
    @Autowired
    private StyleRepo repo;
    
    @GetMapping("style")
    public String getStylePage(Model model){
        List<String> styleList = repo.getStyleList();
        model.addAttribute("styleList", styleList);
        return "homepage";
    }

    @GetMapping("style/breweries")
    public String getBreweriesPage(@RequestParam String style, Model model){
        List<Brewery> breweries = repo.getBreweries(style);
        model.addAttribute("breweries",breweries);
        model.addAttribute("style", style);
        return "breweries";
    }
}
