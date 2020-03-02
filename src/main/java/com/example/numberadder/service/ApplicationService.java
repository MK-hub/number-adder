package com.example.numberadder.service;

import com.example.numberadder.persistence.model.UrlParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Random;

import static java.util.Objects.isNull;

@Service
public class ApplicationService {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    UrlParamsService urlParamsService;
    private Random r = new Random();

    public Integer getNumberFromApi(int minimalValue, int maximalValue) {
        String result = restTemplate
                .getForObject("https://www.random.org/integers/?num=1&min={min}&max={max}&col=1&base=10&format=plain&rnd=new",
                        String.class,
                        minimalValue,
                        maximalValue);
        if (result != null) {
            return Integer.parseInt(result.trim());
        } else {
            log.info("No value from external API. returning 0");
            return 0;
        }
    }

    public String addingNumbers(int minimalValue, int maximalValue) {
        int numberFromApi = getNumberFromApi(minimalValue, maximalValue);
        int numberFromDatabase = getNumberFromDataBase();
        int sum = numberFromApi + numberFromDatabase;
        log.info("Adding two numbers:{} and {}", numberFromApi, numberFromDatabase);
        return String
                .format("Adding two digits: %1d and %2d >> result is %3d", numberFromApi, numberFromDatabase, sum);
    }

    public int getNumberFromDataBase() {
        int rndNumber = r.ints(1, 1, 11).findFirst().getAsInt();
        UrlParams databaseParams = urlParamsService
                .getParametersById(r.ints(1, 4).findFirst().getAsInt());
        if (isNull(databaseParams)) {
            log.info("Empty database, generating rnd number");
            return rndNumber;
        } else {
            return Optional.of(databaseParams.getBaseValue()).orElse(rndNumber);
        }
    }
}
