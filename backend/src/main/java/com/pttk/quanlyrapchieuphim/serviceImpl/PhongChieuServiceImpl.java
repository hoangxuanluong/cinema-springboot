package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.PhongChieu;
import com.pttk.quanlyrapchieuphim.entity.Rap;
import com.pttk.quanlyrapchieuphim.repository.PhongChieuRepository;
import com.pttk.quanlyrapchieuphim.repository.RapRepository;
import com.pttk.quanlyrapchieuphim.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhongChieuServiceImpl implements PhongChieuService {

    @Autowired
    private PhongChieuRepository phongChieuRepository;

    @Autowired
    private RapRepository rapRepository;

    @Override
    public PhongChieu savePhongChieu(PhongChieu phongChieu) {
        Rap rapReq = phongChieu.getRap();
        if (rapReq != null) {
            Rap rap = rapRepository.findById(rapReq.getId()).get();
            phongChieu.setRap(rap);
        }
        return phongChieuRepository.save(phongChieu);
    }

    @Override
    public List<PhongChieu> getAllPhongChieus() {
        return phongChieuRepository.findAll();
    }

    @Override
    public PhongChieu getPhongChieuById(Long id) {
        Optional<PhongChieu> phongChieu = phongChieuRepository.findById(id);
        if (phongChieu.isPresent()) {
            return phongChieu.get();
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("PhongChieu not found with ID: " + id);
            return null;
        }
    }

    @Override
    public void deletePhongChieuById(Long id) {
        phongChieuRepository.deleteById(id);
    }

    @Override
    public PhongChieu updatePhongChieu(Long id, PhongChieu phongChieu) {
        Optional<PhongChieu> existingPhongChieu = phongChieuRepository.findById(id);
        if (existingPhongChieu.isPresent()) {
            PhongChieu updatedPhongChieu = existingPhongChieu.get();
            updatedPhongChieu.setName(phongChieu.getName());
            updatedPhongChieu.setCapacity(phongChieu.getCapacity());
            updatedPhongChieu.setDescription(phongChieu.getDescription());
            // Set other attributes accordingly

            return phongChieuRepository.save(updatedPhongChieu);
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("PhongChieu not found with ID: " + id);
            return null;
        }
    }
}
