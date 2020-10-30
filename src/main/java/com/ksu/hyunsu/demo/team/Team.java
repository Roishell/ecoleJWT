package com.ksu.hyunsu.demo.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ksu.hyunsu.demo.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private String name;

    @Column
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private Set<Member> members;

    public void update(Team team){
        this.name = team.getName();
        this.location = team.getLocation();

    }
}
