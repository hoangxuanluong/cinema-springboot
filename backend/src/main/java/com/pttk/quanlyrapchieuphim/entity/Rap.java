package com.pttk.quanlyrapchieuphim.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String tel;
    private String description;

//
//    @OneToMany(
////            mappedBy = "rap",
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            name = "rap_id",
//            referencedColumnName = "id"
//    )
//
//    private List<PhongChieu> phongChieus;

}
