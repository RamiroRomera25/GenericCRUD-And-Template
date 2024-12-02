package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.dtos.dummy.DummyDtoFilter;
import rami.generic.dtos.dummy.DummyDtoPost;
import rami.generic.dtos.dummy.DummyDtoPut;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreateList;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllPage;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDeleteList;
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
        GenericGetAllPageFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
        GenericCreateList<DummyEntity, Long, DummyModel, DummyDtoPost>,
        GenericSoftDeleteList<DummyEntity, Long, DummyModel>
        {
    List<DummyModel> dummyLike(DummyDtoFilter filter);
}
