package com.project.dancebook.repository;

import com.project.dancebook.entity.Interest;
import com.project.dancebook.entity.InterestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, InterestId> {
}
