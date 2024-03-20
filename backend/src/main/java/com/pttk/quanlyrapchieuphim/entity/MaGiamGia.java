package com.pttk.quanlyrapchieuphim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaGiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ma;
    private float discount;
    private String state;

    @OneToOne(
            mappedBy = "maGiamGia"
    )
    private HoaDon hoaDon;
}
