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
public class Ve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "lichchieu_id",
            referencedColumnName = "id"
    )
    private LichChieu lichChieu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "ghe_id",
            referencedColumnName = "id"
    )
    private Ghe ghe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "hoadon_id",
            referencedColumnName = "id"
    )
    private HoaDon hoaDon;


}
