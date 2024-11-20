package rami.generic.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rami.generic.dtos.DummyDto;

@Service
public class DummyRestClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final String HOST = "http://localhost:";

    public ResponseEntity<DummyDto[]> getAll() {
        return ResponseEntity.ok(restTemplate.getForObject(HOST, DummyDto[].class));
    }
}
