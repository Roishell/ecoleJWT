package com.ksu.hyunsu.demo.member;

import com.ksu.hyunsu.demo.team.Team;
import com.ksu.hyunsu.demo.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor

public class MemberController {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<?> postMember(@RequestBody Member member) {
        memberRepository.save(member);
        return new ResponseEntity<>("Create Member", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getMember() {
        List<Member> members = memberRepository.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Long memberId) {
       Member member = memberRepository.findById(memberId).get();

       return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> putMember(@PathVariable Long memberId, @RequestBody Member member) {
        Member findMember = memberRepository.findById(memberId).get();
        findMember.setName(member.getName());
        memberRepository.save(findMember);
        return new ResponseEntity<>(findMember, HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
        memberRepository.deleteById(memberId);
        return new ResponseEntity<>(memberId + "번 member가 삭제되었습니다.", HttpStatus.OK);
    }

    @PutMapping("/{memberId}/join/{teamId}")
    public ResponseEntity<?> joinTeam(@PathVariable Long memberId, @PathVariable Long teamId){
        Member member = memberRepository.findById(memberId).get();
        Team team = teamRepository.findById(teamId).get();

        member.joinTeam(team);
        Member savedMember = memberRepository.save(member);

        return new ResponseEntity<>(savedMember, HttpStatus.OK);

    }


}
