package com.pttk.quanlyrapchieuphim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LichChieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    private LocalDate date;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "phim_id",
            referencedColumnName = "id"
    )
    private Phim phim;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "phongchieu_id",
            referencedColumnName = "id"
    )
    private PhongChieu phongChieu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "khunggio_id",
            referencedColumnName = "id"
    )
    private KhungGio khungGio;

}
