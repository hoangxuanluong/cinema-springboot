package com.pttk.quanlyrapchieuphim.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ChiTietDichVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "hoadon_id",
            referencedColumnName = "id"
    )
    @JsonBackReference
    private HoaDon hoaDon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "dichvu_id",
            referencedColumnName = "id"
    )
    private DichVu dichVu;
}
