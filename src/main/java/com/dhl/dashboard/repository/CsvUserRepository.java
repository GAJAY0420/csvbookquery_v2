package com.dhl.dashboard.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhl.dashboard.entity.CsvUserModel;

@Repository
public interface CsvUserRepository extends JpaRepository<CsvUserModel, UUID> {
	
	List<CsvUserModel> findByEmpId(String empId);

}
