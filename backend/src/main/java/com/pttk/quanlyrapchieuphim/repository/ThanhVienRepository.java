package com.pttk.quanlyrapchieuphim.repository;

import com.pttk.quanlyrapchieuphim.entity.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThanhVienRepository extends JpaRepository<ThanhVien, Long> {
//    ThanhVien findByUsername(String username);

    Optional<ThanhVien> findByEmail(String email);
}
