package ksp.toy.push.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksp.toy.push.dto.RegisterRequest;
import ksp.toy.push.entity.Member;
import ksp.toy.push.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {

	private final MemberRepository memberRepository;

	@Transactional
	public Member register(RegisterRequest request) {
		final Member member = Member.builder()
			.username(request.getUsername())
			.password(request.getPassword())
			.token(request.getToken())
			.build();
		return memberRepository.save(member);
	}

}
