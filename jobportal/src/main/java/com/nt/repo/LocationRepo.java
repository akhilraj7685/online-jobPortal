package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long>,PagingAndSortingRepository<Location,Long> {

}
