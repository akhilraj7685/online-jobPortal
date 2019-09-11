package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long>,CrudRepository<Skill,Long> {

}
