package com.pttk.quanlyrapchieuphim.serviceImpl;

import com.pttk.quanlyrapchieuphim.entity.ChangePasswordRequest;
import com.pttk.quanlyrapchieuphim.entity.ThanhVien;
import com.pttk.quanlyrapchieuphim.error.ThanhVienNotFoundException;
import com.pttk.quanlyrapchieuphim.repository.ThanhVienRepository;
import com.pttk.quanlyrapchieuphim.service.ThanhVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThanhVienServiceImpl implements ThanhVienService {

    private final PasswordEncoder passwordEncoder;
    private final ThanhVienRepository thanhVienRepository;

    @Override
    public ThanhVien saveThanhVien(ThanhVien thanhVien) {
        return thanhVienRepository.save(thanhVien);
    }

    @Override
    public List<ThanhVien> getAllThanhViens() {
        return thanhVienRepository.findAll();
    }

    @Override
    public ThanhVien getThanhVienById(Long id) throws ThanhVienNotFoundException {
        Optional<ThanhVien> thanhVien = thanhVienRepository.findById(id);
        if (thanhVien.isPresent()) {
            return thanhVien.get();
        } else {
            throw new ThanhVienNotFoundException("user not found");
        }
    }

    @Override
    public void deleteThanhVienById(Long id) {
        thanhVienRepository.deleteById(id);
    }

//    @Override
//    public ThanhVien updateThanhVien(Long id, ThanhVien thanhVien) {
//        Optional<ThanhVien> existingThanhVien = thanhVienRepository.findById(id);
//        if (existingThanhVien.isPresent()) {
//            ThanhVien updatedThanhVien = existingThanhVien.get();
////            updatedThanhVien.setUsername(thanhVien.getUsername());
//            updatedThanhVien.setPassword(thanhVien.getPassword());
//            updatedThanhVien.setName(thanhVien.getName());
//            updatedThanhVien.setDate(thanhVien.getDate());
//            updatedThanhVien.setAddress(thanhVien.getAddress());
//            updatedThanhVien.setEmail(thanhVien.getEmail());
//            updatedThanhVien.setTel(thanhVien.getTel());
//            updatedThanhVien.setPoint(thanhVien.getPoint());
//            // Set other attributes accordingly
//
//            return thanhVienRepository.save(updatedThanhVien);
//        } else {
//            // Handle not found exception or return null/throw exception
//            // For example:
//            // throw new NotFoundException("ThanhVien not found with ID: " + id);
//            return null;
//        }
//    }

//    @Override
//    public ThanhVien login(ThanhVien thanhVien) throws ThanhVienNotFoundException {
//        ThanhVien tv = thanhVienRepository.findByUsername(thanhVien.getUsername());
//        if (tv != null) {
//            if (tv.getPassword().equals(thanhVien.getPassword())) {
//                return tv;
//            }
//        }
//        throw new ThanhVienNotFoundException("username/password incorrect!!!");
//
//    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (ThanhVien) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        thanhVienRepository.save(user);
    }
}
