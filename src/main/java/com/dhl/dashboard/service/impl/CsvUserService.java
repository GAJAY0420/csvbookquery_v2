package com.dhl.dashboard.service.impl;

import com.dhl.dashboard.dto.UserDto;
import com.dhl.dashboard.entity.CsvUserModel;
import com.dhl.dashboard.repository.CsvUserRepository;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CsvUserService implements UserDetailsService {
    @Autowired
    private CsvUserRepository csvUserRepo;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {
        System.out.println("<<< loadUserByUsername >>>");
        List<CsvUserModel> users = csvUserRepo.findByEmpId(empId);
        System.out.println("user: --->" + users);
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("User not found with username: " + empId);
        }
        CsvUserModel user = users.get(0);
        return new User(user.getEmpId(), user.getPassword(),
                new ArrayList<>());
    }

    public CsvUserModel save(UserDto userDto) {
    	CsvUserModel obj = null;
    	
    	try {
			CsvUserModel csvUser = new CsvUserModel();
			csvUser.setEmpId(userDto.getEmpID());
			csvUser.setFirstName(userDto.getFirstName());
			csvUser.setLastName(userDto.getLastName());
			csvUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));
			csvUser.setConfirmPassword(bcryptEncoder.encode(userDto.getConfirmPassword()));
			csvUser.setEmail(userDto.getEmail());
			csvUser.setMobileNo(userDto.getMobileNo());
			System.out.println("csvUser \n" + csvUser);
			obj = csvUserRepo.save(csvUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return obj;
    }

}
