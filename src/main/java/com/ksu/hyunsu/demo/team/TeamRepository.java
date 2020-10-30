package com.ksu.hyunsu.demo.team;

import com.ksu.hyunsu.demo.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team, Long > {
}
