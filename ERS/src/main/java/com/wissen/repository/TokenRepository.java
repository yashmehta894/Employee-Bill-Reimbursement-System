package com.wissen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.wissen.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

	Token findByEmpId(int empid);

	Token findByAuthToken(String token);

	//
	// @Query(value = "delete from token where id = ?1",nativeQuery = true)
	// Token delete(int id);
	//
	void deleteByAuthToken(String token);
//	void removeByAuthToken(String token);
}
