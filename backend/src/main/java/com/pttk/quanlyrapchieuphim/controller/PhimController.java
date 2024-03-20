package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.Phim;
import com.pttk.quanlyrapchieuphim.service.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/phims")
@CrossOrigin(origins = "http://localhost:3000")
public class PhimController {

    @Autowired
    PhimService phimService;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public Phim savePhim(@RequestBody Phim phim) {
        return phimService.savePhim(phim);
    }

    @GetMapping("")
    public List<Phim> getListPhim() {
        return phimService.getListPhim();
    }

    @GetMapping("/{id}")
    public Phim getPhimById(@PathVariable("id") Long phimId) {
        return phimService.getPhimById(phimId);
    }

    @DeleteMapping("/{id}")
    public String deletePhimById(@PathVariable("id") Long phimId) {
        phimService.deletePhimById(phimId);
        return "delete phim successfully";
    }

    @PutMapping("/{id}")
    public Phim updatePhim(@PathVariable("id") Long phimId,
                           @RequestBody Phim phim) {
        return phimService.updatePhim(phimId, phim);
    }

}
