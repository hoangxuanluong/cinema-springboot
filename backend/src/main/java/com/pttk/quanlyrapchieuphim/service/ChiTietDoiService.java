package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.ChiTietDoi;

import java.util.List;

public interface ChiTietDoiService {
    ChiTietDoi saveChiTietDoi(ChiTietDoi chiTietDoi);

    List<ChiTietDoi> getListChiTietDoi();

    ChiTietDoi getChiTietDoiById(Long chiTietDoiId);

    void deleteChiTietDoiById(Long chiTietDoiId);

    ChiTietDoi updateChiTietDoi(Long chiTietDoiId, ChiTietDoi chiTietDoi);
}
