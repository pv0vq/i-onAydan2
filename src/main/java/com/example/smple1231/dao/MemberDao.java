package com.example.smple1231.dao;

import com.example.smple1231.membervo.ANIMAL_INS;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberDao {
     List<ANIMAL_INS> selectAll();

     ANIMAL_INS selectlist(String id);

     Long insertani(ANIMAL_INS animal_ins);

     Long updateani(String id ,ANIMAL_INS aNIMAL_INS);

     Long deleteani(String id);

}
