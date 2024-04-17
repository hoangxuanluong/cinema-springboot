package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.ChiTietDoi;
import com.pttk.quanlyrapchieuphim.service.ChiTietDoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chitietdois")
@CrossOrigin(origins = "http://localhost:3000")
public class ChiTietDoiController {

    @Autowired
    ChiTietDoiService chiTietDoiService;

    @PostMapping("")
    public ChiTietDoi saveChiTietDoi(@RequestBody ChiTietDoi chiTietDoi) {
        return chiTietDoiService.saveChiTietDoi(chiTietDoi);
    }

    @GetMapping("")
    public List<ChiTietDoi> getListChiTietDoi() {
        return chiTietDoiService.getListChiTietDoi();
    }

    @GetMapping("/{id}")
    public ChiTietDoi getChiTietDoiById(@PathVariable("id") Long chiTietDoiId) {
        return chiTietDoiService.getChiTietDoiById(chiTietDoiId);
    }

    @DeleteMapping("/{id}")
    public String deleteChiTietDoiById(@PathVariable("id") Long chiTietDoiId) {
        chiTietDoiService.deleteChiTietDoiById(chiTietDoiId);
        return "delete chi tiet doi successfully";
    }

    @PutMapping("/{id}")
    public ChiTietDoi updateChiTietDoi(@PathVariable("id") Long chiTietDoiId,
                                       @RequestBody ChiTietDoi chiTietDoi) {
        return chiTietDoiService.updateChiTietDoi(chiTietDoiId, chiTietDoi);
    }

}
