package com.hauxy.turistguide2.controller;

import com.hauxy.turistguide2.model.TouristAttraction;
import com.hauxy.turistguide2.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {
    final TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAttractions(Model model) {
        List<TouristAttraction> attractionsList = service.getTouristAttractions();
        model.addAttribute("listofattractions", attractionsList);
        return "attractionList";
    }

    @GetMapping("{name}")
    public ResponseEntity<TouristAttraction> getAttraction(@PathVariable String name) {
        return new ResponseEntity<>(service.getAttraction(name), HttpStatus.OK);
    }


    @GetMapping("/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getAttraction(name);
        model.addAttribute("attraction", attraction);
        return "tags";
    }

    @GetMapping("add")
    public String addNewAttraction(Model model) {
        TouristAttraction t = new TouristAttraction();
        model.addAttribute("TouristAttraction", t);
        model.addAttribute("ListOfTags", service.getAllTags());
        model.addAttribute("ListOfCities",service.getAllCities());
        return "createAttraction";
    }


    @PostMapping("save")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        service.addTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }


    @PostMapping("{name}/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        service.updateTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("delete/{name}")
    public ResponseEntity<List<TouristAttraction>> deleteAttraction(@PathVariable String name) {
        service.removeTouristAttraction(name);
        return new ResponseEntity<>(service.getTouristAttractions(), HttpStatus.OK);
    }

    @GetMapping("{name}/edit")
    public String getAttractionEdit(@PathVariable String name, Model model) {
        TouristAttraction attraction = service.getAttraction(name);
        model.addAttribute("attraction", attraction);
        model.addAttribute("ListOfCities", service.getAllCities());
        model.addAttribute("ListOfTags", service.getAllTags());
        return "attractionEdit";
    }


}
