package com.korea.todo.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.korea.todo.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {
	//비밀키
	private static final String SECRET_KEY="2fa503f15cf4a2b2b5bfa79bb4cc50d4ddd8caae4d9c865cfe794b44f72b49a88265ce19bf3b3e901935f1f9a3b9131df4889f3d4a9bc6763405bc194428a60c2d30d61d6f44360c8c286d8a90a1eadd7968df2e2ca47909ed5caf05facc2327a0ac545f13d2df19cbc0669c15d1e902617006f469a1c7800a5d00555d1b6e4d205425964a81800e079191bb067bcea468003de8f8ab6dc49a19975da2c40fffcb1b0a5fa9517c8d17099c41f2c3ed526e974c04ed7b714c7e3317e5a56e25d75bdd63b4340e492fa3b4b02d9ba2c6ca490fc55fa3c97a8247d8bcc0fc00794a8b855451db2f20c633bc0194d58133e0ca0932ba0fc164aa103d77a1d25212f4f2560f48e69e132b402c7516fabd093c71475257114450d3ad6c2cfc28eb7dee09c1c068b68ff9919edd6791783c89dedf99f3428e975641dd728970cb2e6507bccff975ef69be724fd32a44fbca0a7ccb6908c9b4e1deff0a9d3804fa0ca8628119e54d51dda81a8c5942c037c78019561f47ff69dcbc0365f1769ec6d0399cf4e1175f0492193ccb40b2aa90f5db537ab95b37ae857b1160972c119ca8399f6cb643d8f0a628d629c1342b4e2e6dd41b73d275b556945fee0ec67c8148f88475a6fc205592c44c40d28e37d63697a85d166f2a92e20c0d2f18a526ff99180a15ded5f27b26ab960bb503b37ed7ef3679b32cb4a95af3fab85d8a3fc3b294c2";
	
	//토큰을 만드는 메서드
	public String create(UserEntity entity) {
		//토큰 만료시간을 설정
		//현재 시각 + 1일
		//instant클래스 : 타임스탬프로 찍음
		//plus() : 첫번째 인자는 더할 양, 시간 단위
		//ChronoUnit열거형의 DAYS 일 단위를 의미함
		Date expiryDate = Date.from(Instant.now().plus(1,ChronoUnit.DAYS));
		
		
		//JWT토큰을 생성
		return Jwts.builder()
				//header에 들어갈  내용 및 서명을 하기 위한 SECRET_KEY
					.signWith(SignatureAlgorithm.HS512,SECRET_KEY) //헤더 + 서명 알고리즘 설정
					.setSubject(entity.getId()) //sub 클레임 : 사용자 고유 ID
					.setIssuer("todo app") //iss 클레임 : 토큰 발급자
					.setIssuedAt(new Date()) //iat 클레임 : 발급 시각
					.setExpiration(expiryDate) //exp 클레임 : 만료 시간
					.compact();//최종 직렬화된 토큰 문자열 반환
	}
	
	public String validateAndGetUserId(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(SECRET_KEY) //서명 검증용 키 설정
							.parseClaimsJws(token) //토큰 파싱 및 서명 검증
							.getBody(); //내부 페이로드(Claims)획득
		
		return claims.getSubject(); //sub 클레임(사용자 ID) 반환
	}
	
	//create()메서드는 JWT라이브러리를 이용해 JWT토큰을 생성함
	//토큰을 생성하는 과정에서 우리가 임의로 지정한 SECRET_KEY를 개인키로 사용함
	//validateAndGetUserId()는 토큰을 디코딩, 파싱 및 위조여부를 확인함
	//이후에 우리가 원하는 유저의 ID를 반환함
	//라이브러리 덕분에 JSON을 생성, 서명, 인코딩, 디코딩, 파싱하는 작업을 직접하지 않아도 됨
	//TokenProvider를 작성했으니 로그인을 하면 create()메서드를 통해 토큰을 생성하고 UserDTO에 
	
	
}
