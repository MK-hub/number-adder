package com.example.numberadder.persistence.repo;

import com.example.numberadder.persistence.model.UrlParamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlParamsRepository extends JpaRepository<UrlParamsEntity, Integer> {}
