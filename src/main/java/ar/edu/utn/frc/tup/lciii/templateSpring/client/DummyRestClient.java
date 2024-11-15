package ar.edu.utn.frc.tup.lciii.templateSpring.client;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DummyRestClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final String HOST = "http://localhost:";

    public ResponseEntity<DummyDto[]> getAll() {
        return ResponseEntity.ok(restTemplate.getForObject(HOST, DummyDto[].class));
    }
}
