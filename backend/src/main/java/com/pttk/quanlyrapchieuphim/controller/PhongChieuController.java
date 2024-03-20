package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.PhongChieu;
import com.pttk.quanlyrapchieuphim.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phongchieus")
public class PhongChieuController {

    @Autowired
    private PhongChieuService phongChieuService;

    @PostMapping
    public PhongChieu createPhongChieu(@RequestBody PhongChieu phongChieu) {
        return phongChieuService.savePhongChieu(phongChieu);
    }

    @GetMapping
    public List<PhongChieu> getAllPhongChieus() {
        return phongChieuService.getAllPhongChieus();
    }

    @GetMapping("/{id}")
    public PhongChieu getPhongChieuById(@PathVariable Long id) {
        return phongChieuService.getPhongChieuById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePhongChieu(@PathVariable Long id) {
        phongChieuService.deletePhongChieuById(id);
        return "Deleted PhongChieu with ID: " + id;
    }

    @PutMapping("/{id}")
    public PhongChieu updatePhongChieu(@PathVariable Long id, @RequestBody PhongChieu phongChieu) {
        return phongChieuService.updatePhongChieu(id, phongChieu);
    }
}
