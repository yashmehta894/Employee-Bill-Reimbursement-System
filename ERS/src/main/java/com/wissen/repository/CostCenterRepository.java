package com.wissen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wissen.model.Costcenter;

public interface CostCenterRepository extends JpaRepository<Costcenter, Integer> {

	@Query(value = "select * from costcenter", nativeQuery = true)
	public List<Costcenter> getCostCenters();

}
