package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.Ve;

import java.util.List;

public interface VeService {
    Ve saveVe(Ve ve);

    List<Ve> getAllVes();

    Ve getVeById(Long id);

    void deleteVeById(Long id);

    Ve updateVe(Long id, Ve ve);

    List<Ve> findVeByLichChieu(Long lichChieuId);
}
