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
public class Ghe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberChair;
    private String type;
    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "phongchieu_id",
            referencedColumnName = "id"
    )
    private PhongChieu phongChieu;
}
