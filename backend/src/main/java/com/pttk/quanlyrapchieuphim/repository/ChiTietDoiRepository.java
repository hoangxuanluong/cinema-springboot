package com.pttk.quanlyrapchieuphim.repository;

import com.pttk.quanlyrapchieuphim.entity.ChiTietDoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietDoiRepository extends JpaRepository<ChiTietDoi, Long> {
}
