package ksp.toy.push.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ksp.toy.push.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
