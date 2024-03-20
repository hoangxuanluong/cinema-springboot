package com.pttk.quanlyrapchieuphim.repository;

import com.pttk.quanlyrapchieuphim.entity.LichChieu;
import com.pttk.quanlyrapchieuphim.entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichChieuRepository extends JpaRepository<LichChieu, Long> {

    List<LichChieu> findByPhim(Phim phim);
}
