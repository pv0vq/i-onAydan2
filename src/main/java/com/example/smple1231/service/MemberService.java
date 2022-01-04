package com.example.smple1231.service;

import com.example.smple1231.dao.MemberDao;
import com.example.smple1231.membervo.ANIMAL_INS;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {


    private MemberDao memberDao;
    public MemberService(MemberDao memberDao) {this.memberDao = memberDao;}

    @Transactional
    public List<ANIMAL_INS> aniAllService(){
        return memberDao.selectAll();
    } //동물 리스트 조회 서비스

    @Transactional
    public ANIMAL_INS getaniService(String id){
        return memberDao.selectlist(id);
    } //동물 상세 조회 서비스

    @Transactional
    public Long aniaddService(ANIMAL_INS aNIMAL_INS){
        return memberDao.insertani(aNIMAL_INS);
    } //동물 작성 서비스

    @Transactional
    public Long aniputService(String id, ANIMAL_INS aNIMAL_INS){return memberDao.updateani(id, aNIMAL_INS);} //동물 수정 서비스

    public Long anidelService(String id){
        return memberDao.deleteani(id);
    } // 동물 삭제 서비스
}
