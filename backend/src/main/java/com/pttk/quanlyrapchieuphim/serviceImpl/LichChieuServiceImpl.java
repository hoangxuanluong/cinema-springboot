package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.LichChieu;
import com.pttk.quanlyrapchieuphim.entity.Phim;
import com.pttk.quanlyrapchieuphim.repository.LichChieuRepository;
import com.pttk.quanlyrapchieuphim.service.LichChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LichChieuServiceImpl implements LichChieuService {

    @Autowired
    private LichChieuRepository lichChieuRepository;

    @Override
    public LichChieu saveLichChieu(LichChieu lichChieu) {
        return lichChieuRepository.save(lichChieu);
    }

    @Override
    public List<LichChieu> getAllLichChieus() {
        return lichChieuRepository.findAll();
    }

    @Override
    public LichChieu getLichChieuById(Long id) {
        Optional<LichChieu> lichChieu = lichChieuRepository.findById(id);
        if (lichChieu.isPresent()) {
            return lichChieu.get();
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("LichChieu not found with ID: " + id);
            return null;
        }
    }

    @Override
    public void deleteLichChieuById(Long id) {
        lichChieuRepository.deleteById(id);
    }

    @Override
    public LichChieu updateLichChieu(Long id, LichChieu lichChieu) {
        Optional<LichChieu> existingLichChieu = lichChieuRepository.findById(id);
        if (existingLichChieu.isPresent()) {
            LichChieu updatedLichChieu = existingLichChieu.get();
            updatedLichChieu.setPrice(lichChieu.getPrice());
            updatedLichChieu.setDate(lichChieu.getDate());
            updatedLichChieu.setPhim(lichChieu.getPhim());
            updatedLichChieu.setPhongChieu(lichChieu.getPhongChieu());
            updatedLichChieu.setKhungGio(lichChieu.getKhungGio());
            // Set other attributes accordingly

            return lichChieuRepository.save(updatedLichChieu);
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("LichChieu not found with ID: " + id);
            return null;
        }
    }

    @Override
    public List<LichChieu> findLichChieuByPhimId(Long phimId) {
        Phim phim = new Phim();
        phim.setId(phimId); // Khởi tạo đối tượng Phim với ID được cung cấp
        System.out.println(lichChieuRepository.findByPhim(phim));
        return lichChieuRepository.findByPhim(phim);
    }
}
