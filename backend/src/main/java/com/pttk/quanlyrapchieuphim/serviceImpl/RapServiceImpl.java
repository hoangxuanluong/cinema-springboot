package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.Rap;
import com.pttk.quanlyrapchieuphim.repository.RapRepository;
import com.pttk.quanlyrapchieuphim.service.RapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RapServiceImpl implements RapService {

    @Autowired
    RapRepository rapRepository;

    @Override
    public Rap saveRap(Rap rap) {
        return rapRepository.save(rap);
    }

    @Override
    public List<Rap> getListRap() {
        return rapRepository.findAll();
    }

    @Override
    public Rap getRapById(Long rapId) {
        Optional<Rap> rap = rapRepository.findById(rapId);
        if (!rap.isPresent()) {
            // Throw an exception or handle the situation when rap is not found
            System.out.println("Rap not found");
            // throw new NotFoundException("Rap not found with ID: " + rapId);
        }
        return rap.get();
    }

    @Override
    public void deleteRapById(Long rapId) {
        rapRepository.deleteById(rapId);
    }

    @Override
    public Rap updateRap(Long rapId, Rap rap) {
        Rap oldRap = rapRepository.findById(rapId).get();
        oldRap.setName(rap.getName());
        oldRap.setAddress(rap.getAddress());
        oldRap.setTel(rap.getTel());
        oldRap.setDescription(rap.getDescription());
        // Set other attributes accordingly

        return rapRepository.save(oldRap);
    }
}
