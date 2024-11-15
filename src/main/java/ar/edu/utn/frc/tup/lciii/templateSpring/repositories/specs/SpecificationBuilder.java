package ar.edu.utn.frc.tup.lciii.templateSpring.repositories.specs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpecificationBuilder<E> {

    @Autowired
    private GenericSpecification<E> specification;

    private Specification<E> specs;

    public SpecificationBuilder<E> withDynamicFilter(Map<String, Object> attributes) {
        specs.and(specification.dynamicFilter(attributes));
        return this;
    }

    public SpecificationBuilder<E> withValueDynamicFilter(String value, String... entityFields) {
        specs.and(specification.valueDynamicFilter(value, entityFields));
        return this;
    }

    public <T extends Comparable<? super T>> SpecificationBuilder<E> withFilterBetween(T lower, T higher, String field) {
        specs.and(specification.filterBetween(lower, higher, field));
        return this;
    }

    public <T extends Comparable<? super T>> SpecificationBuilder<E> withFilterLowerThan(T value, String field) {
        specs.and(specification.filterLowerThan(value, field));
        return this;
    }

    public <T extends Comparable<? super T>> SpecificationBuilder<E> withFilterGreaterThan(T value, String field) {
        specs.and(specification.filterGreaterThan(value, field));
        return this;
    }

    public SpecificationBuilder<E> withDynamicFilterLike(Map<String, Object> attributes) {
        specs.and(specification.dynamicFilterLike(attributes));
        return this;
    }

    public Specification<E> build() {
        return specs;
    }
}
