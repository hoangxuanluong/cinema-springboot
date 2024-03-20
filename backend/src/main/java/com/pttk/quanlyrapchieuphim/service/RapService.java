package com.pttk.quanlyrapchieuphim.service;

import com.pttk.quanlyrapchieuphim.entity.Rap;

import java.util.List;

public interface RapService {
    Rap saveRap(Rap rap);

    List<Rap> getListRap();

    Rap getRapById(Long rapId);

    void deleteRapById(Long rapId);

    Rap updateRap(Long rapId, Rap rap);
}
