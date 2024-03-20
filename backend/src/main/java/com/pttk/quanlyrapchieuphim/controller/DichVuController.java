package com.pttk.quanlyrapchieuphim.controller;


import com.pttk.quanlyrapchieuphim.entity.DichVu;
import com.pttk.quanlyrapchieuphim.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dichvus")
@CrossOrigin(origins = "http://localhost:3000")
public class DichVuController {

    @Autowired
    private DichVuService dichVuService;

    @PostMapping
    public DichVu createDichVu(@RequestBody DichVu dichVu) {
        return dichVuService.saveDichVu(dichVu);
    }

    @GetMapping
    public List<DichVu> getAllDichVus() {
        return dichVuService.getAllDichVus();
    }

    @GetMapping("/{id}")
    public DichVu getDichVuById(@PathVariable Long id) {
        return dichVuService.getDichVuById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDichVu(@PathVariable Long id) {
        dichVuService.deleteDichVuById(id);
        return "Deleted DichVu with ID: " + id;
    }

    @PutMapping("/{id}")
    public DichVu updateDichVu(@PathVariable Long id, @RequestBody DichVu dichVu) {
        return dichVuService.updateDichVu(id, dichVu);
    }
}
