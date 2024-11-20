package rami.generic.repositories.specs;

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
        if (specs == null) {
            specs = specification.dynamicFilter(attributes);
        } else {
            specs.and(specification.dynamicFilter(attributes));
        }
        return this;
    }

    public SpecificationBuilder<E> withValueDynamicFilter(String value, String... entityFields) {
        if (specs == null) {
            specs = specification.valueDynamicFilter(value, entityFields);
        } else {
            specs.and(specification.valueDynamicFilter(value, entityFields));
        }
        return this;
    }

    public <T extends Comparable<? super T>> SpecificationBuilder<E> withFilterBetween(T lower, T higher, String field) {
        if (specs == null) {
            specs = specification.filterBetween(lower, higher, field);
        } else {
            specs.and(specification.filterBetween(lower, higher, field));
        }
        return this;
    }

    public <T extends Comparable<? super T>> SpecificationBuilder<E> withFilterLowerThan(T value, String field) {
        if (specs == null) {
            specs = specification.filterLowerThan(value, field);
        } else {
            specs.and(specification.filterLowerThan(value, field));
        }
        return this;
    }

    public <T extends Comparable<? super T>> SpecificationBuilder<E> withFilterGreaterThan(T value, String field) {
        if (specs == null) {
            specs = specification.filterGreaterThan(value, field);
        } else {
            specs.and(specification.filterGreaterThan(value, field));
        }
        return this;
    }

    public SpecificationBuilder<E> withDynamicFilterLike(Map<String, Object> attributes) {
        if (specs == null) {
            specs = specification.dynamicFilterLike(attributes);
        } else {
            specs.and(specification.dynamicFilterLike(attributes));
        }
        return this;
    }

    public SpecificationBuilder<E> uniqueValue(String field, Object value) {
        if (specs == null) {
            specs = specification.uniqueValue(field, value);
        } else {
            specs.and(specification.uniqueValue(field, value));
        }
        return this;
    }

    public SpecificationBuilder<E> compositeUniqueValues(Map<String, Object> uniqueFields) {
        if (uniqueFields.isEmpty()) {
            throw new IllegalArgumentException("Unique fields cannot be empty.");
        }

        if (specs == null) {
            specs = specification.compositeUniqueValues(uniqueFields);
        } else {
            specs.and(specification.compositeUniqueValues(uniqueFields));
        }
        return this;
    }

    public Specification<E> build() {
        Specification<E> specsForReturn = this.specs;
        specs = null;
        return specsForReturn;
    }
}
