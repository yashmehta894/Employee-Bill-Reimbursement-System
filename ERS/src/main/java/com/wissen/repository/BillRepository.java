package com.wissen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {

}
