package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.HoaDon;

import java.util.List;

public interface HoaDonService {
    HoaDon saveHoaDon(HoaDon hoaDon);

    List<HoaDon> getAllHoaDons();

    HoaDon getHoaDonById(Long id);

    void deleteHoaDonById(Long id);

    HoaDon updateHoaDon(Long id, HoaDon hoaDon);
}
