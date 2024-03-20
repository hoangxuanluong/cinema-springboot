package com.pttk.quanlyrapchieuphim.controller;

import com.pttk.quanlyrapchieuphim.entity.ChangePasswordRequest;
import com.pttk.quanlyrapchieuphim.entity.ThanhVien;
import com.pttk.quanlyrapchieuphim.error.ThanhVienNotFoundException;
import com.pttk.quanlyrapchieuphim.service.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/thanhviens")
@CrossOrigin(origins = "http://localhost:3000")
public class ThanhVienController {

    @Autowired
    private ThanhVienService thanhVienService;

    @PostMapping
    public ThanhVien createThanhVien(@RequestBody ThanhVien thanhVien) {
        return thanhVienService.saveThanhVien(thanhVien);
    }

//    @PostMapping("/login")
//    public ThanhVien login(@RequestBody ThanhVien thanhVien) throws ThanhVienNotFoundException {
//        return thanhVienService.login(thanhVien);
//    }

    @GetMapping
    public List<ThanhVien> getAllThanhViens() {
        return thanhVienService.getAllThanhViens();
    }

    @GetMapping("/{id}")
    public ThanhVien getThanhVienById(@PathVariable Long id) throws ThanhVienNotFoundException {
        return thanhVienService.getThanhVienById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteThanhVien(@PathVariable Long id) {
        thanhVienService.deleteThanhVienById(id);
        return "Deleted ThanhVien with ID: " + id;
    }

//    @PutMapping("/{id}")
//    public ThanhVien updateThanhVien(@PathVariable Long id, @RequestBody ThanhVien thanhVien) {
//        return thanhVienService.updateThanhVien(id, thanhVien);
//    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        thanhVienService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}
