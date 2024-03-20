package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.DichVu;
import com.pttk.quanlyrapchieuphim.repository.DichVuRepository;
import com.pttk.quanlyrapchieuphim.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DichVuServiceImpl implements DichVuService {

    @Autowired
    private DichVuRepository dichVuRepository;

    @Override
    public DichVu saveDichVu(DichVu dichVu) {
        return dichVuRepository.save(dichVu);
    }

    @Override
    public List<DichVu> getAllDichVus() {
        return dichVuRepository.findAll();
    }

    @Override
    public DichVu getDichVuById(Long id) {
        Optional<DichVu> dichVu = dichVuRepository.findById(id);
        if (dichVu.isPresent()) {
            return dichVu.get();
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("DichVu not found with ID: " + id);
            return null;
        }
    }

    @Override
    public void deleteDichVuById(Long id) {
        dichVuRepository.deleteById(id);
    }

    @Override
    public DichVu updateDichVu(Long id, DichVu dichVu) {
        Optional<DichVu> existingDichVu = dichVuRepository.findById(id);
        if (existingDichVu.isPresent()) {
            DichVu updatedDichVu = existingDichVu.get();
            updatedDichVu.setName(dichVu.getName());
            updatedDichVu.setPrice(dichVu.getPrice());
            updatedDichVu.setPoint(dichVu.getPoint());
            updatedDichVu.setImage(dichVu.getImage());
            updatedDichVu.setDescription(dichVu.getDescription());
            // Set other attributes accordingly

            return dichVuRepository.save(updatedDichVu);
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("DichVu not found with ID: " + id);
            return null;
        }
    }
}
