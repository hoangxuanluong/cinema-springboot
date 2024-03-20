package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.Ve;
import com.pttk.quanlyrapchieuphim.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ves")
@CrossOrigin(origins = "http://localhost:3000")
public class VeController {

    @Autowired
    private VeService veService;

    @PostMapping
    public Ve createVe(@RequestBody Ve ve) {
        return veService.saveVe(ve);
    }

    @GetMapping
    public List<Ve> getAllVes() {
        return veService.getAllVes();
    }

    @GetMapping("/{id}")
    public Ve getVeById(@PathVariable Long id) {
        return veService.getVeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteVe(@PathVariable Long id) {
        veService.deleteVeById(id);
        return "Deleted Ve with ID: " + id;
    }

    @PutMapping("/{id}")
    public Ve updateVe(@PathVariable Long id, @RequestBody Ve ve) {
        return veService.updateVe(id, ve);
    }

    @GetMapping("/lichchieus/{lichChieuId}")
    public List<Ve> getVeByLichChieu(@PathVariable Long lichChieuId) {
        return veService.findVeByLichChieu(lichChieuId);
    }
}
