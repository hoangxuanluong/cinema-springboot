package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.DichVu;

import java.util.List;

public interface DichVuService {
    DichVu saveDichVu(DichVu dichVu);

    List<DichVu> getAllDichVus();

    DichVu getDichVuById(Long id);

    void deleteDichVuById(Long id);

    DichVu updateDichVu(Long id, DichVu dichVu);
}
