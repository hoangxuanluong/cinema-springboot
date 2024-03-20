package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.*;
import com.pttk.quanlyrapchieuphim.repository.DichVuRepository;
import com.pttk.quanlyrapchieuphim.repository.HoaDonRepository;
import com.pttk.quanlyrapchieuphim.repository.ThanhVienRepository;
import com.pttk.quanlyrapchieuphim.repository.VeRepository;
import com.pttk.quanlyrapchieuphim.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Autowired
    private DichVuRepository dichVuRepository;

    @Autowired
    private VeRepository veRepository;

    @Override
    public HoaDon saveHoaDon(HoaDon hoaDon) {
        hoaDon.setDate(LocalDate.now());
        System.out.println(hoaDon);
        ThanhVien thanhVien = thanhVienRepository.findByEmail(hoaDon.getThanhVien().getEmail()).get();
        System.out.println(thanhVien);
        hoaDon.setThanhVien(thanhVien);
        List<ChiTietDichVu> chiTietDichVuRequests = hoaDon.getChiTietDichVus();
        List<ChiTietDichVu> chiTietDichVus = new ArrayList<>();
        int total = 0;
        for (ChiTietDichVu chiTietDichVuRequest : chiTietDichVuRequests) {
            ChiTietDichVu chiTietDichVu = new ChiTietDichVu();

            DichVu dichVu = dichVuRepository.findById(chiTietDichVuRequest.getDichVu().getId()).orElse(null);
            if (dichVu != null) {
                chiTietDichVu.setDichVu(dichVu);
                chiTietDichVu.setPrice(dichVu.getPrice());
                chiTietDichVu.setQuantity(chiTietDichVuRequest.getQuantity());
                chiTietDichVus.add(chiTietDichVu);
            } else {
                return null;
            }
        }
        hoaDon.setChiTietDichVus(chiTietDichVus);
        List<Ve> veRequests = hoaDon.getVes();
        System.out.println(veRequests);
        List<Ve> ves = new ArrayList<>();

        for (Ve veRequest : hoaDon.getVes()) {

            Ve ve = veRepository.findById(veRequest.getId()).orElse(null);
            ves.add(ve);
//            ve.setHoaDon(savedHoaDon); // Set HoaDon cho mỗi Ve
//            veRepository.save(ve);
        }
        hoaDon.setVes(ves);

        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
        return savedHoaDon;
    }


    @Override
    public List<HoaDon> getAllHoaDons() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon getHoaDonById(Long id) {
        Optional<HoaDon> hoaDon = hoaDonRepository.findById(id);
        if (hoaDon.isPresent()) {
            return hoaDon.get();
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("HoaDon not found with ID: " + id);
            return null;
        }
    }

    @Override
    public void deleteHoaDonById(Long id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public HoaDon updateHoaDon(Long id, HoaDon hoaDon) {
        Optional<HoaDon> existingHoaDon = hoaDonRepository.findById(id);
        if (existingHoaDon.isPresent()) {
            HoaDon updatedHoaDon = existingHoaDon.get();
            updatedHoaDon.setDate(hoaDon.getDate());
            updatedHoaDon.setThanhVien(hoaDon.getThanhVien());
            updatedHoaDon.setMaGiamGia(hoaDon.getMaGiamGia());
            updatedHoaDon.setHinhThucThanhToan(hoaDon.getHinhThucThanhToan());
            updatedHoaDon.setVes(hoaDon.getVes());
            updatedHoaDon.setChiTietDichVus(hoaDon.getChiTietDichVus());
            // Set other attributes accordingly

            return hoaDonRepository.save(updatedHoaDon);
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("HoaDon not found with ID: " + id);
            return null;
        }
    }
}
