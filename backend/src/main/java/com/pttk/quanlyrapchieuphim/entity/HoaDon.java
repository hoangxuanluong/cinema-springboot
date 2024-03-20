package com.pttk.quanlyrapchieuphim.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "thanhvien_id",
            referencedColumnName = "id"
    )
    private ThanhVien thanhVien;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "magiamgia_id",
            referencedColumnName = "id"
    )
    private MaGiamGia maGiamGia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "hinhthucthanhtoan_id",
            referencedColumnName = "id"
    )
    private HinhThucThanhToan hinhThucThanhToan;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "hoadon_id",
            referencedColumnName = "id"
    )
    @JsonBackReference
    private List<Ve> ves;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "hoadon_id",
            referencedColumnName = "id"
    )
    private List<ChiTietDichVu> chiTietDichVus;

    @Override
    public String toString() {
        return "HoaDon{" +
                "id=" + id +
                ", date=" + date +
                ", thanhVien=" + (thanhVien != null ? thanhVien.getId() : "null") +
                ", maGiamGia=" + maGiamGia +
                ", hinhThucThanhToan=" + hinhThucThanhToan +
                ", ves=" + ves +
                ", chiTietDichVus=" + chiTietDichVus +
                '}';
    }
}
