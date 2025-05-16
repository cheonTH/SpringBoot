package com.korea.member.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.member.dto.MemberDTO;
import com.korea.member.model.MemberEntity;
import com.korea.member.model.ResponseDTO;
import com.korea.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    // 전체 조회
    @GetMapping
    public ResponseEntity<?> findAllMember() {
        List<MemberDTO> dto = service.findAllMember();
        ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(dto).build();
        return ResponseEntity.ok().body(response);
    }

    // 이메일 조회
    @GetMapping("/{email}")
    public ResponseEntity<?> findEmailMember(@PathVariable String email) {
        List<MemberDTO> dto = service.findByEmailMember(email);
        ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(dto).build();
        return ResponseEntity.ok().body(response);
    }

    // 회원 추가
    @PostMapping
    public ResponseEntity<?> saveMember(@RequestBody MemberDTO dto) {
        MemberEntity entity = MemberDTO.toEntity(dto);
        List<MemberDTO> members = service.saveMember(entity);
        ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(members).build();
        return ResponseEntity.ok(response);
    }
    
    
    @PutMapping("/{email}/password")
    public ResponseEntity<?> updatePassword(@PathVariable String email, @RequestBody String password) {
        List<MemberDTO> updatedMembers = service.updatePassword(email, password);
        
        ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(updatedMembers).build();
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable int id) {
        List<MemberDTO> members = service.deleteMember(id);
        ResponseDTO<MemberDTO> response = ResponseDTO.<MemberDTO>builder().data(members).build();
        return ResponseEntity.ok(response);
    }
   
    
}