package com.hauxy.turistguide2.controller;

import com.hauxy.turistguide2.model.TouristAttraction;
import com.hauxy.turistguide2.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristController {
    TouristService service;

    public TouristController(TouristService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<TouristAttraction>> getAttractions() {
        List<TouristAttraction> attractions = service.getTouristAttractions();
        return new ResponseEntity<>(attractions, HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<TouristAttraction> getAttraction(@PathVariable String name) {
        return new ResponseEntity<>(service.getAttraction(name), HttpStatus.OK);
    }
    @GetMapping("{name}/tags")
    public ResponseEntity<List<TouristAttraction>> getAttractionTags(@PathVariable String name) {
        return new ResponseEntity<>(service.getTags(), HttpStatus.OK);

    }


    @PostMapping("add")
    public ResponseEntity<List<TouristAttraction>> addAttraction(@RequestBody TouristAttraction touristAttraction) {
        service.addTouristAttraction(touristAttraction);
        return new ResponseEntity<>(service.getTouristAttractions(), HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<TouristAttraction> updateAttraction(@RequestBody TouristAttraction touristAttraction) {
        service.updateTouristAttraction(touristAttraction.getName(), touristAttraction.getDescription());
        return new ResponseEntity<>(service.getAttraction(touristAttraction.getName()), HttpStatus.OK);
    }

    @PostMapping("delete/{name}")
    public ResponseEntity<List<TouristAttraction>> deleteAttraction(@PathVariable String name) {
        service.removeTouristAttraction(name);
        return new ResponseEntity<>(service.getTouristAttractions(), HttpStatus.OK);
    }

}
