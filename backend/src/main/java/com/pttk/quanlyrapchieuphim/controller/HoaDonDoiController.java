package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.HoaDonDoi;
import com.pttk.quanlyrapchieuphim.service.HoaDonDoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoadondois")
@CrossOrigin(origins = "http://localhost:3000")
public class HoaDonDoiController {

    @Autowired
    private HoaDonDoiService hoaDonDoiService;

    @PostMapping
    public HoaDonDoi createHoaDonDoi(@RequestBody HoaDonDoi hoaDonDoi) {
        return hoaDonDoiService.saveHoaDonDoi(hoaDonDoi);
    }

    @GetMapping
    public List<HoaDonDoi> getAllHoaDonDois() {
        return hoaDonDoiService.getAllHoaDonDois();
    }

    @GetMapping("/{id}")
    public HoaDonDoi getHoaDonDoiById(@PathVariable Long id) {
        return hoaDonDoiService.getHoaDonDoiById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteHoaDonDoi(@PathVariable Long id) {
        hoaDonDoiService.deleteHoaDonDoiById(id);
        return "Deleted HoaDonDoi with ID: " + id;
    }

    @PutMapping("/{id}")
    public HoaDonDoi updateHoaDonDoi(@PathVariable Long id, @RequestBody HoaDonDoi hoaDonDoi) {
        return hoaDonDoiService.updateHoaDonDoi(id, hoaDonDoi);
    }
}
