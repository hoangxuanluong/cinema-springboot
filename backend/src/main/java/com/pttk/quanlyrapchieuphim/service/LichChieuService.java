package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.LichChieu;

import java.util.List;

public interface LichChieuService {

    List<LichChieu> findLichChieuByPhimId(Long phimId);

    LichChieu saveLichChieu(LichChieu lichChieu);

    List<LichChieu> getAllLichChieus();

    LichChieu getLichChieuById(Long id);

    void deleteLichChieuById(Long id);

    LichChieu updateLichChieu(Long id, LichChieu lichChieu);
}
