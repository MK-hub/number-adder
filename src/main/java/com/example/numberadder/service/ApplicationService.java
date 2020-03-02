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

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationService.class);

    private static final int RANDOM_NUMBER_ORIGIN = 1;
    private static final int RANDOM_NUMBER_BOUND = 11;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UrlParamsService urlParamsService;

    private Random r = new Random();

    public Integer getNumberFromApi(int minimalValue, int maximalValue) {
        String result = restTemplate
                .getForObject("https://www.random.org/integers/"
                                + "?num=1&min={min}&max={max}&col=1&base=10&format=plain&rnd=new",
                        String.class,
                        minimalValue,
                        maximalValue);
        if (result != null) {
            return Integer.parseInt(result.trim());
        } else {
            LOG.info("No value from external API. returning 0");
            return 0;
        }
    }

    public String addingNumbers(int minimalValue, int maximalValue) {
        int numberFromApi = getNumberFromApi(minimalValue, maximalValue);
        int numberFromDatabase = getNumberFromDataBaseOrRandom();
        int sum = numberFromApi + numberFromDatabase;
        LOG.info("Adding two numbers:{} and {}", numberFromApi, numberFromDatabase);
        return String
                .format("Adding two digits: %1d and %2d equals %3d", numberFromApi, numberFromDatabase, sum);
    }

    public int getNumberFromDataBaseOrRandom() {
        int rndNumber = r.ints(1, RANDOM_NUMBER_ORIGIN, RANDOM_NUMBER_BOUND).findFirst().getAsInt();
        int databaseBound = Math.toIntExact(urlParamsService.count() + 1);
        UrlParams databaseParams = urlParamsService
                .getParametersById(r.ints(RANDOM_NUMBER_ORIGIN, databaseBound).findFirst().getAsInt());
        if (isNull(databaseParams)) {
            LOG.info("Empty database, generating rnd number");
            return rndNumber;
        } else {
            return Optional.of(databaseParams.getBaseValue()).orElse(rndNumber);
        }
    }
}
