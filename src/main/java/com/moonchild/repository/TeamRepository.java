package com.moonchild.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moonchild.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{

}
