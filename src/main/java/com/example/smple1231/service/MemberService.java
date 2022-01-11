package com.example.smple1231.service;

import com.example.smple1231.dao.MemberDao;
import com.example.smple1231.membervo.AnimalIns;
import com.example.smple1231.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private  AnimalRepository animalRepository;

    private MemberDao memberDao;
    public MemberService(MemberDao memberDao) {this.memberDao = memberDao;}

    @Transactional
    public List<AnimalIns> aniAllService(){ //동물 리스트 조회 서비스
        return animalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AnimalIns> getaniService(Long id){//동물 상세 조회 서비스
        return animalRepository.findById(id);
    }

    @Transactional
    public AnimalIns aniaddService(AnimalIns animalIns){//동물 작성 서비스
        AnimalIns animal = new AnimalIns();
        animal.setAnimalType(animalIns.getAnimalType());
        animal.setContext(animalIns.getContext());
        animal.setIntakeCondition(animalIns.getIntakeCondition());
        animal.setSexUponIntake(animalIns.getSexUponIntake());
        animal.setName(animalIns.getName());
        animal.setDateTime(new Date());
        return animalRepository.save(animal);
    }

    @Transactional
    public String aniputService(Long id, AnimalIns animalIns){//동물 수정 서비스
       AnimalIns animal =  animalRepository.findById(id).orElseThrow(()->{
                return new IllegalArgumentException("글 찾기 실패: 아이디가 없다");}
                );
        animal.setAnimalType(animalIns.getAnimalType());
        animal.setContext(animalIns.getContext());
        animal.setIntakeCondition(animalIns.getIntakeCondition());
        animal.setSexUponIntake(animalIns.getSexUponIntake());
        animal.setName(animalIns.getName());
        animal.setDateTime(new Date());
        return "동물수정완료";
    }

    public String anidelService(Long id){// 동물 삭제 서비스
        animalRepository.deleteById(id);
        return "동물삭제완료";
    }
}
