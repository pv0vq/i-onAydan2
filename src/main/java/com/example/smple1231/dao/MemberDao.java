package com.example.smple1231.dao;

import com.example.smple1231.membervo.ANIMAL_INS;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberDao {  // membermapper.xml와 연결

     List<ANIMAL_INS> selectAll(); // 전체 동물 리스트 Dao

     ANIMAL_INS selectlist(String id); // 상세 동물 리스트 Dao

     Long insertani(ANIMAL_INS animal_ins); // 동물 추가 Dao

     Long updateani(String id ,ANIMAL_INS aNIMAL_INS); // 동물 수정 Dao

     Long deleteani(String id); // 동물 삭제 Dao

}
