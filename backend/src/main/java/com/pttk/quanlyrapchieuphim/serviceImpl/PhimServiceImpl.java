package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.Phim;
import com.pttk.quanlyrapchieuphim.repository.PhimRepository;
import com.pttk.quanlyrapchieuphim.service.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhimServiceImpl implements PhimService {

    @Autowired
    PhimRepository phimRepository;

    @Override
    public Phim savePhim(Phim phim) {
        return phimRepository.save(phim);
    }

    @Override
    public List<Phim> getListPhim() {
        return phimRepository.findAll();
    }

    @Override
    public Phim getPhimById(Long phimId) {
        Optional<Phim> phim = phimRepository.findById(phimId);
        if (!phim.isPresent()) {
            //exception
            System.out.println("phim not found");
        }
        return phim.get();
    }

    @Override
    public void deletePhimById(Long phimId) {
        phimRepository.deleteById(phimId);
    }

    @Override
    public Phim updatePhim(Long phimId, Phim phim) {
        Phim oldPhim = phimRepository.findById(phimId).get();
        oldPhim.setName(phim.getName());
        oldPhim.setDescription(phim.getDescription());
        oldPhim.setType(phim.getType());
        oldPhim.setProductionYear(phim.getProductionYear());
        return phimRepository.save(oldPhim);
    }
}
