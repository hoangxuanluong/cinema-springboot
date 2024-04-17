package com.pttk.quanlyrapchieuphim.repository;

import com.pttk.quanlyrapchieuphim.entity.Ghe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GheRepository extends JpaRepository<Ghe, Long> {
}
