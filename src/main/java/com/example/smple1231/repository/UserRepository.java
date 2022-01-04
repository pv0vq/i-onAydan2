package com.example.smple1231.repository;


import com.example.smple1231.auth.PrincipalDetail;
import com.example.smple1231.membervo.HooMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<HooMember, Integer> {

	Optional<HooMember> findByUsername(String username); // 시큐리티 Repository

}
