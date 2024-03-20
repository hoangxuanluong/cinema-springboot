package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.PhongChieu;

import java.util.List;

public interface PhongChieuService {
    PhongChieu savePhongChieu(PhongChieu phongChieu);

    List<PhongChieu> getAllPhongChieus();

    PhongChieu getPhongChieuById(Long id);

    void deletePhongChieuById(Long id);

    PhongChieu updatePhongChieu(Long id, PhongChieu phongChieu);
}
