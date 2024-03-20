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
public class ChiTietDoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int point;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "hoadondoi_id",
            referencedColumnName = "id"
    )
    private HoaDonDoi hoaDonDoi;

    @ManyToOne()
    @JoinColumn(
            name = "dichvu_id",
            referencedColumnName = "id"
    )
    private DichVu dichVu;
}
