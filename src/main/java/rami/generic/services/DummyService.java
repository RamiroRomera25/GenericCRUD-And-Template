package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.dtos.DummyDtoFilter;
import rami.generic.dtos.DummyDtoPost;
import rami.generic.dtos.DummyDtoPut;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;

import java.util.List;

@Service
public interface DummyService extends GenericCRUDService<DummyEntity, Long, DummyModel, DummyDtoPost, DummyDtoPut, DummyDtoFilter> {
    List<DummyModel> dummyLike(DummyDtoFilter filter);
}
