package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.Ve;
import com.pttk.quanlyrapchieuphim.repository.LichChieuRepository;
import com.pttk.quanlyrapchieuphim.repository.VeRepository;
import com.pttk.quanlyrapchieuphim.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeServiceImpl implements VeService {

    @Autowired
    private VeRepository veRepository;

    @Autowired
    private LichChieuRepository lichChieuRepository;

    @Override
    public Ve saveVe(Ve ve) {
        return veRepository.save(ve);
    }

    @Override
    public List<Ve> getAllVes() {
        return veRepository.findAll();
    }

    @Override
    public Ve getVeById(Long id) {
        Optional<Ve> ve = veRepository.findById(id);
        if (ve.isPresent()) {
            return ve.get();
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("Ve not found with ID: " + id);
            return null;
        }
    }

    @Override
    public void deleteVeById(Long id) {
        veRepository.deleteById(id);
    }

    @Override
    public Ve updateVe(Long id, Ve ve) {
        Optional<Ve> existingVe = veRepository.findById(id);
        if (existingVe.isPresent()) {
            Ve updatedVe = existingVe.get();
            updatedVe.setLichChieu(ve.getLichChieu());
            updatedVe.setGhe(ve.getGhe());
            // Set other attributes accordingly

            return veRepository.save(updatedVe);
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("Ve not found with ID: " + id);
            return null;
        }
    }

    @Override
    public List<Ve> findVeByLichChieu(Long lichChieuId) {
//        LichChieu lichChieu = new LichChieu();
//        lichChieu.setId(lichChieuId);
        System.out.println(lichChieuRepository.findById(lichChieuId).get());
        return veRepository.findByLichChieu(lichChieuRepository.findById(lichChieuId).get());
    }
}
