package com.example.smple1231.repository;


import com.example.smple1231.auth.PrincipalDetail;
import com.example.smple1231.membervo.HooMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//DAO
//자동으로 Bean 등록이 된다
//@Repository 생략가능
public interface UserRepository extends JpaRepository<HooMember, Integer> {

	Optional<HooMember> findByUsername(String username);

}
