package com.ksu.hyunsu.demo.team;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping(value="/api/teams")
@RestController
@RequiredArgsConstructor

public class TeamController {

    private final TeamRepository teamRepository;

    @PostMapping
    ResponseEntity<?> postTeam(@RequestBody Team team){
        teamRepository.save(team);
        return new ResponseEntity<>("Create Team", HttpStatus.CREATED);
    }

    @GetMapping("/{teamId}")
    ResponseEntity<?> getTeam(@PathVariable Long teamId) {
        teamRepository.findById(teamId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"not team"));
        Team team = teamRepository.findById(teamId).get();
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @PutMapping("/{teamId}")
    ResponseEntity<?> putTeam(@PathVariable Long teamId, @RequestBody Team team){
        Team savedTeam = teamRepository.findById(teamId).get();
        savedTeam.update(team);
        Team result = teamRepository.save(savedTeam);

        return new ResponseEntity<>(result, HttpStatus.CREATED.OK);

    }
    @DeleteMapping("/{teamId}")
    ResponseEntity<?> deleteTeam(@PathVariable Long teamId){
        teamRepository.deleteById(teamId);
        return new ResponseEntity<>(teamId + "번 team이 삭제되었습니다.", HttpStatus.OK);

    }
}
