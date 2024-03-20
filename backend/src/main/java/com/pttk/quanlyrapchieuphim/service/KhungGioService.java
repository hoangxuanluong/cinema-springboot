package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.KhungGio;

import java.util.List;

public interface KhungGioService {
    KhungGio saveKhungGio(KhungGio khungGio);

    List<KhungGio> getAllKhungGios();

    KhungGio getKhungGioById(Long id);

    void deleteKhungGioById(Long id);

    KhungGio updateKhungGio(Long id, KhungGio khungGio);
}
