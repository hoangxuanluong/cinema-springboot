package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.ChangePasswordRequest;
import com.pttk.quanlyrapchieuphim.entity.ThanhVien;
import com.pttk.quanlyrapchieuphim.error.ThanhVienNotFoundException;

import java.security.Principal;
import java.util.List;

public interface ThanhVienService {


    ThanhVien saveThanhVien(ThanhVien thanhVien);

    List<ThanhVien> getAllThanhViens();

    ThanhVien getThanhVienById(Long id) throws ThanhVienNotFoundException;

    void deleteThanhVienById(Long id);

//    ThanhVien updateThanhVien(Long id, ThanhVien thanhVien);

//    ThanhVien login(ThanhVien thanhVien) throws ThanhVienNotFoundException;

    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
