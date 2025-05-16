package com.korea.member.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.korea.member.dto.MemberDTO;
import com.korea.member.model.MemberEntity;
import com.korea.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository repository;
	
	//조회
	public List<MemberDTO> findAllMember(){
		return repository.findAll().stream().map(MemberDTO::new).collect(Collectors.toList());
	}
	//이메일로 조회
	public List<MemberDTO> findByEmailMember(String email){
		Optional<MemberEntity> option = repository.findByEmail(email);
		MemberDTO dto;
		if(option.isPresent()) {
			MemberEntity entity = option.get();
		}
		
		return repository.findAll().stream()
	            .map(dto -> new MemberDTO(dto))
	            .collect(Collectors.toList());
	}
	
	
	//추가
	public List<MemberDTO> saveMember(MemberEntity entity) {
	    repository.save(entity);

	    return repository.findAll()
	                     .stream()
	                     .map(MemberDTO::new)
	                     .collect(Collectors.toList());
	}
	
	
	//변경
	public List<MemberDTO> updatePassword(String email, String Password) {
	    Optional<MemberEntity> optionalMember = repository.findByEmail(email);
	    
	    MemberEntity member = optionalMember.get();
	    
	    member.setPassword(Password);
	    
	    repository.save(member);
	    
	    return repository.findAll()
	                     .stream()
	                     .map(MemberDTO::new)
	                     .collect(Collectors.toList());
	}
	
	
	
	//삭제
	public List<MemberDTO> deleteMember(int id) {
        repository.deleteById(id);

        return repository.findAll()
                         .stream()
                         .map(MemberDTO::new)
                         .collect(Collectors.toList());
    }
}
