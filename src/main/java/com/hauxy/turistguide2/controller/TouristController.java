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
    model.addAttribute("ListOfCities", service.getAllCities());
    return "createAttraction";
}

    @PostMapping("add")
    public String saveAttraction(@ModelAttribute TouristAttraction t, Model model) {
        boolean success = service.saveAttraction(t);

        if (!success) {
            model.addAttribute("error", "Navn må ikke være tomt");
            model.addAttribute("TouristAttraction", t);
            model.addAttribute("ListOfTags", service.getAllTags());
            model.addAttribute("ListOfCities", service.getAllCities());
            return "createAttraction"; // reload form with error
        }

        return "redirect:/attractions";
    }

    @PostMapping("save")
    public String addAttraction(@ModelAttribute TouristAttraction touristAttraction, Model model) {
        if (touristAttraction.getName() == null || touristAttraction.getName().trim().isEmpty()) {
            model.addAttribute("error", "Navn må ikke være tomt");
            model.addAttribute("TouristAttraction", touristAttraction);
            model.addAttribute("ListOfTags", service.getAllTags());
            model.addAttribute("ListOfCities", service.getAllCities());
            return "redirect:/createAttraction";
        }

        service.addTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }




    @PostMapping("{name}/update")
    public String updateAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        service.updateTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        service.removeTouristAttraction(name);
        return "redirect:/attractions";
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
