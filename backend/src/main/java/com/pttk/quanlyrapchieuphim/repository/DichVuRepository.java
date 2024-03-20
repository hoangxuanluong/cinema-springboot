package com.pttk.quanlyrapchieuphim.repository;

import com.pttk.quanlyrapchieuphim.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu, Long> {
}
