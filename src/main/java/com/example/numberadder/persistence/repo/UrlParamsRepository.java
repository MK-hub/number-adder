package com.example.numberadder.persistence.repo;

import com.example.numberadder.persistence.model.UrlParams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlParamsRepository extends JpaRepository<UrlParams, Integer> {
}
