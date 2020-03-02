package com.example.numberadder.service;

import com.example.numberadder.persistence.model.UrlParams;
import com.example.numberadder.persistence.repo.UrlParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlParamsService {

    @Autowired
    private UrlParamsRepository urlParamsRepository;

    public UrlParams getParametersById(int id) {
        return urlParamsRepository.findById(id).orElse(null);
    }

    public long count() {
        return urlParamsRepository.count();
    }
}
