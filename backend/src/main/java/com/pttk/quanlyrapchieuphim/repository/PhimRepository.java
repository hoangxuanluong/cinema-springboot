package com.pttk.quanlyrapchieuphim.repository;

import com.pttk.quanlyrapchieuphim.entity.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhimRepository extends JpaRepository<Phim, Long> {
}
