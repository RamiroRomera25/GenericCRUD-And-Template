package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDtoFilter;
import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDtoPost;
import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDtoPut;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.DummyEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DummyService extends GenericCRUDService<DummyEntity, Long, DummyModel, DummyDtoPost, DummyDtoPut, DummyDtoFilter> {
    List<DummyModel> dummyLike(DummyDtoFilter filter);
}
