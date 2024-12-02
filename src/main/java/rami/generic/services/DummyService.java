package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.dtos.dummy.DummyDtoFilter;
import rami.generic.dtos.dummy.DummyDtoPost;
import rami.generic.dtos.dummy.DummyDtoPut;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.services.genericSegregation.basicCRUD.ServiceCreate;
import rami.generic.services.genericSegregation.basicCRUD.ServiceCreateList;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetAllPage;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetById;
import rami.generic.services.genericSegregation.basicCRUD.ServiceSoftDelete;
import rami.generic.services.genericSegregation.basicCRUD.ServiceSoftDeleteList;
import rami.generic.services.genericSegregation.basicCRUD.ServiceUpdate;
import rami.generic.services.genericSegregation.filters.ServiceGetAllListFilter;
import rami.generic.services.genericSegregation.filters.ServiceGetAllPageFilter;

import java.util.List;

@Service
public interface DummyService
extends ServiceGetAllPage<DummyEntity, Long, DummyModel>,
        ServiceGetAllList<DummyEntity, Long, DummyModel>,
        ServiceGetById<DummyEntity, Long, DummyModel>,
        ServiceCreate<DummyEntity, Long, DummyModel, DummyDtoPost>,
        ServiceUpdate<DummyEntity, Long, DummyModel, DummyDtoPut>,
        ServiceSoftDelete<DummyEntity, Long, DummyModel>,
        ServiceGetAllListFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
        ServiceGetAllPageFilter<DummyEntity, Long, DummyModel, DummyDtoFilter>,
        ServiceCreateList<DummyEntity, Long, DummyModel, DummyDtoPost>,
        ServiceSoftDeleteList<DummyEntity, Long, DummyModel>
        {
    List<DummyModel> dummyLike(DummyDtoFilter filter);
}
