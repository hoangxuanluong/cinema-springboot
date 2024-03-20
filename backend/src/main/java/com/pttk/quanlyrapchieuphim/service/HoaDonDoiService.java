package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.HoaDonDoi;

import java.util.List;

public interface HoaDonDoiService {
    HoaDonDoi saveHoaDonDoi(HoaDonDoi hoaDonDoi);

    List<HoaDonDoi> getAllHoaDonDois();

    HoaDonDoi getHoaDonDoiById(Long id);

    void deleteHoaDonDoiById(Long id);

    HoaDonDoi updateHoaDonDoi(Long id, HoaDonDoi hoaDonDoi);
}
