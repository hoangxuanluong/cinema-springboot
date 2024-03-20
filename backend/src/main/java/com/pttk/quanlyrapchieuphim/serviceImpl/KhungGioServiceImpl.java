package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.KhungGio;
import com.pttk.quanlyrapchieuphim.repository.KhungGioRepository;
import com.pttk.quanlyrapchieuphim.service.KhungGioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhungGioServiceImpl implements KhungGioService {

    @Autowired
    private KhungGioRepository khungGioRepository;

    @Override
    public KhungGio saveKhungGio(KhungGio khungGio) {
        return khungGioRepository.save(khungGio);
    }

    @Override
    public List<KhungGio> getAllKhungGios() {
        return khungGioRepository.findAll();
    }

    @Override
    public KhungGio getKhungGioById(Long id) {
        Optional<KhungGio> khungGio = khungGioRepository.findById(id);
        if (khungGio.isPresent()) {
            return khungGio.get();
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("KhungGio not found with ID: " + id);
            return null;
        }
    }

    @Override
    public void deleteKhungGioById(Long id) {
        khungGioRepository.deleteById(id);
    }

    @Override
    public KhungGio updateKhungGio(Long id, KhungGio khungGio) {
        Optional<KhungGio> existingKhungGio = khungGioRepository.findById(id);
        if (existingKhungGio.isPresent()) {
            KhungGio updatedKhungGio = existingKhungGio.get();
            updatedKhungGio.setTimeStart(khungGio.getTimeStart());
            updatedKhungGio.setTimeEnd(khungGio.getTimeEnd());
            // Set other attributes accordingly

            return khungGioRepository.save(updatedKhungGio);
        } else {
            // Handle not found exception or return null/throw exception
            // For example:
            // throw new NotFoundException("KhungGio not found with ID: " + id);
            return null;
        }
    }
}
