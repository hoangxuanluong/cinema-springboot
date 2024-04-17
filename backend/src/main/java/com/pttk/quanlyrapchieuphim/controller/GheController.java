package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.Ghe;
import com.pttk.quanlyrapchieuphim.service.GheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ghes")
@CrossOrigin(origins = "http://localhost:3000")
public class GheController {

    @Autowired
    GheService gheService;

    @PostMapping("")
    public Ghe saveGhe(@RequestBody Ghe ghe) {
        return gheService.saveGhe(ghe);
    }

    @GetMapping("")
    public List<Ghe> getListGhe() {
        return gheService.getListGhe();
    }

    @GetMapping("/{id}")
    public Ghe getGheById(@PathVariable("id") Long gheId) {
        return gheService.getGheById(gheId);
    }

    @DeleteMapping("/{id}")
    public String deleteGheById(@PathVariable("id") Long gheId) {
        gheService.deleteGheById(gheId);
        return "delete ghe successfully";
    }

    @PutMapping("/{id}")
    public Ghe updateGhe(@PathVariable("id") Long gheId,
                         @RequestBody Ghe ghe) {
        return gheService.updateGhe(gheId, ghe);
    }

}
