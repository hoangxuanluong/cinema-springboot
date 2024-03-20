package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.Phim;

import java.util.List;

public interface PhimService {
    Phim savePhim(Phim phim);

    List<Phim> getListPhim();

    Phim getPhimById(Long phimId);

    void deletePhimById(Long phimId);

    Phim updatePhim(Long phimId, Phim phim);
}
