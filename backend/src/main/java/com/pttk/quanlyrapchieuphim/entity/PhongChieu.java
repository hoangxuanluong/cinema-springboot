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
public class PhongChieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "rap_id",
            referencedColumnName = "id"
    )
    private Rap rap;


//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "phongchieu_id",
//            referencedColumnName = "id"
//    )
//
//    private List<Ghe> ghes;

}
