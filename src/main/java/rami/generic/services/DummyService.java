package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.dtos.DummyDtoFilter;
import rami.generic.dtos.DummyDtoPost;
import rami.generic.dtos.DummyDtoPut;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllPage;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;
import rami.generic.services.genericSegregation.basicCRUD.GenericUpdate;
import rami.generic.services.genericSegregation.filters.GenericGetAllListFilter;
import rami.generic.services.genericSegregation.filters.GenericGetAllPageFilter;

import java.util.List;

@Service
public interface DummyService
extends GenericGetAllPage<DummyEntity, Long, DummyModel>,
        GenericGetAllList<DummyEntity, Long, DummyModel>,
        GenericGetById<DummyEntity, Long, DummyModel>,
        GenericCreate<DummyEntity, Long, DummyModel, DummyDtoPost>,
        GenericUpdate<DummyEntity, Long, DummyModel, DummyDtoPut>,
        GenericSoftDelete<DummyEntity, Long, DummyModel>,
        GenericGetAllListFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
        GenericGetAllPageFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>
        {
    List<DummyModel> dummyLike(DummyDtoFilter filter);
}
