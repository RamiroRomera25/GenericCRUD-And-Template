package rami.generic.services.impl;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rami.generic.dtos.DummyDtoFilter;
import rami.generic.dtos.DummyDtoPost;
import rami.generic.entities.DummyEntity;
import rami.generic.models.DummyModel;
import rami.generic.repositories.DummyRepository;
import rami.generic.repositories.GenericRepository;
import rami.generic.repositories.specs.GenericSpecification;
import rami.generic.repositories.specs.SpecificationBuilder;
import rami.generic.services.DummyService;

import java.util.List;

@Service
@Profile("firebase")
public class DummyServiceImplFirebase implements DummyService {

    //#region Autowired
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    private GenericSpecification<DummyEntity> dummySpecification;

    @Autowired
    private SpecificationBuilder<DummyEntity> specificationBuilder;
    //#endregion

    //#region Override for Generic
    @Override
    public ModelMapper getMapper() {
        return modelMapper;
    }

    @Override
    public GenericRepository<DummyEntity, Long> getRepository() {
        return dummyRepository;
    }

    @Override
    public Class<DummyEntity> entityClass() {
        return DummyEntity.class;
    }

    @Override
    public Class<DummyModel> modelClass() {
        return DummyModel.class;
    }

    @Override
    public SpecificationBuilder<DummyEntity> specificationBuilder() {
        return specificationBuilder;
    }
    //#endregion

    @Override
    public List<DummyModel> dummyLike(DummyDtoFilter filter) {
        List<DummyEntity> entityList = getRepository().findAll(specificationBuilder
                .withDynamicFilterLike(this.getFilterMap(filter))
                .build());
        if (!entityList.isEmpty()) {
            return getMapper().map(entityList, new TypeToken<List<DummyModel>>() {}.getType());
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content retrieved.");
        }
    }

    @Override
    public DummyModel create(DummyDtoPost dtoPost) {

        DummyEntity entityToSave = getMapper().map(dtoPost, entityClass());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        String uniqueId = databaseReference.push().getKey();

        databaseReference.child(getCollectionName()).child(uniqueId).setValueAsync(entityToSave);

        return getMapper().map(entityToSave, modelClass());
    }

    private String getCollectionName() {
        return "dummy";
    }
}
