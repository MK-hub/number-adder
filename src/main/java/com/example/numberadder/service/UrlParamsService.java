package com.example.numberadder.service;

import com.example.numberadder.persistence.repo.UrlParamsRepository;
import com.example.numberadder.persistence.model.UrlParamsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlParamsService {

    @Autowired
    UrlParamsRepository urlParamsRepository;

    public UrlParamsEntity getParametersById(int id) {
        return urlParamsRepository.findById(id).orElse(null);
    }
}
