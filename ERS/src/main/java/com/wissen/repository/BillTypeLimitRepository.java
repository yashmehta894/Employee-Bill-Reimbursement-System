package com.wissen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wissen.model.Billtypelimit;
import com.wissen.model.BilltypelimitPK;

public interface BillTypeLimitRepository extends JpaRepository<Billtypelimit, BilltypelimitPK>{

}
