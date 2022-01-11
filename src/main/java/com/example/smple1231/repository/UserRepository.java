package com.example.smple1231.repository;


import com.example.smple1231.entity.HooUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<HooUser, Long> {

	@EntityGraph(attributePaths = "authorities") //해당쿼리수행시 razy조회말고 eager조회로 받아옴
	Optional<HooUser> findOneWithAuthoritiesByUsername(String username);// username을 기준으로 유저정보와 권한정보를 가져옴

	@EntityGraph(attributePaths = "authorities") //해당쿼리수행시 razy조회말고 eager조회로 받아옴
	List<HooUser> findAll();  // 유저 전체 리스트

	Optional<HooUser> findByUsername(String username); // 유저네임으로 조회 Repository
}
