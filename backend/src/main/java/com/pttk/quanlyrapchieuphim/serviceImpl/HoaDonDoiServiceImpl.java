package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.ChiTietDoi;
import com.pttk.quanlyrapchieuphim.entity.DichVu;
import com.pttk.quanlyrapchieuphim.entity.HoaDonDoi;
import com.pttk.quanlyrapchieuphim.entity.ThanhVien;
import com.pttk.quanlyrapchieuphim.repository.DichVuRepository;
import com.pttk.quanlyrapchieuphim.repository.HoaDonDoiRepository;
import com.pttk.quanlyrapchieuphim.repository.ThanhVienRepository;
import com.pttk.quanlyrapchieuphim.service.HoaDonDoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonDoiServiceImpl implements HoaDonDoiService {

    @Autowired
    private HoaDonDoiRepository hoaDonDoiRepository;

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Autowired
    private DichVuRepository dichVuRepository;


    @Override
    public HoaDonDoi saveHoaDonDoi(HoaDonDoi hoaDonDoi) {
        hoaDonDoi.setDate(LocalDate.now());
        ThanhVien thanhVien = thanhVienRepository.findById(hoaDonDoi.getThanhVien().getId()).get();
        hoaDonDoi.setThanhVien(thanhVien);
        List<ChiTietDoi> chiTietDoiRequests = hoaDonDoi.getChiTietDois();
        List<ChiTietDoi> chiTietDois = new ArrayList<>();
        int total = 0;
        for (ChiTietDoi chiTietDoiRequest : chiTietDoiRequests) {
            ChiTietDoi chiTietDoi = new ChiTietDoi();
            // Ánh xạ thông tin dịch vụ từ cơ sở dữ liệu (ví dụ: dựa trên mã dịch vụ trong chiTietDoiRequest)
            DichVu dichVu = dichVuRepository.findById(chiTietDoiRequest.getDichVu().getId()).orElse(null);
            if (dichVu != null) {
                chiTietDoi.setDichVu(dichVu);
                chiTietDoi.setPoint(dichVu.getPoint());
                chiTietDoi.setQuantity(chiTietDoiRequest.getQuantity());
                total += chiTietDoi.getPoint() * chiTietDoi.getQuantity();
                // Cài đặt các thông tin khác cho chiTietDoi nếu cần
                // ...
                chiTietDois.add(chiTietDoi);
//                thanhVienService.updateThanhVien(thanhVien.getId(), thanhVien);
            } else {
                // Xử lý trường hợp không tìm thấy dịch vụ
                return null;
            }
        }
        thanhVien.setPoint(thanhVien.getPoint() - total);

        return hoaDonDoiRepository.save(hoaDonDoi);
    }

    @Override
    public List<HoaDonDoi> getAllHoaDonDois() {
        return hoaDonDoiRepository.findAll();
    }

    @Override
    public HoaDonDoi getHoaDonDoiById(Long id) {
        Optional<HoaDonDoi> hoaDonDoi = hoaDonDoiRepository.findById(id);
        if (hoaDonDoi.isPresent()) {
            return hoaDonDoi.get();
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("HoaDonDoi not found with ID: " + id);
            return null;
        }
    }

    @Override
    public void deleteHoaDonDoiById(Long id) {
        hoaDonDoiRepository.deleteById(id);
    }

    @Override
    public HoaDonDoi updateHoaDonDoi(Long id, HoaDonDoi hoaDonDoi) {
        Optional<HoaDonDoi> existingHoaDonDoi = hoaDonDoiRepository.findById(id);
        if (existingHoaDonDoi.isPresent()) {
            HoaDonDoi updatedHoaDonDoi = existingHoaDonDoi.get();
            updatedHoaDonDoi.setDate(hoaDonDoi.getDate());
            updatedHoaDonDoi.setThanhVien(hoaDonDoi.getThanhVien());
            updatedHoaDonDoi.setChiTietDois(hoaDonDoi.getChiTietDois());
            // Set other attributes accordingly

            return hoaDonDoiRepository.save(updatedHoaDonDoi);
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("HoaDonDoi not found with ID: " + id);
            return null;
        }
    }
}
