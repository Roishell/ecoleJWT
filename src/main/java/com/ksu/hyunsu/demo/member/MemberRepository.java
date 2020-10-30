package com.ksu.hyunsu.demo.member;

import com.ksu.hyunsu.demo.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
