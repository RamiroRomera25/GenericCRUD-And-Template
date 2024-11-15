package ar.edu.utn.frc.tup.lciii.templateSpring.repositories.specs;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.base.BaseEntity;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;

/**
 * Specification to filter any entity by created date.
 * @param <E> entity that extends from base entity
 */
@NoArgsConstructor
@Component
public class GenericSpecification<E extends BaseEntity> {
    
    /**
     * Filter by attributtes.
     * @param attributes att for dinamyc search
     *  On key value u will put the name of the att and
     *  in the value the "value" that u want to search on
     *  that att.
     * @return Specification att.
     */
    public Specification<E> dynamicFilter(Map<String, Object> attributes) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicates = criteriaBuilder.conjunction();

            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value != null) {
                    predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get(key), value));
                }
            }

            return predicates;
        };
    }

    /**
     * Filter by attributtes.
     * @param value value for search.
     * @param entityFields the fields that you want to filter on
     * ur entity.
     * @return Specification att.
     */
    public Specification<E> valueDynamicFilter(String value, String... entityFields) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicates = criteriaBuilder.conjunction();

            if (value != null) {
                String likePattern = "%" + value.toLowerCase(Locale.ROOT) + "%";
                Predicate orPredicates = criteriaBuilder.disjunction();

                for (String field : entityFields) {
                    orPredicates = criteriaBuilder.or(
                            orPredicates,
                            criteriaBuilder.like(criteriaBuilder.lower(root.get(field).as(String.class)), likePattern)
                    );
                }

                predicates = criteriaBuilder.and(predicates, orPredicates);
            }

            return predicates;
        };
    }

    public <T extends Comparable<? super T>> Specification<E> filterBetween(T lower, T higher, String field) {
        return (root, query, criteriaBuilder) -> {
            if (lower == null && higher == null) {
                throw new NullPointerException("Null Pointer inputs on filter between.");
            }

            if (higher.compareTo(lower) < 0) {
                throw new IllegalArgumentException("Invalid inputs on filter between.");
            }

            return criteriaBuilder.between(root.get(field), lower, higher);
        };
    }

    public <T extends Comparable<? super T>> Specification<E> filterLowerThan(T value, String field) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                throw new IllegalArgumentException("Invalid input on filter lower than.");
            }

            return criteriaBuilder.lessThan(root.get(field), value);
        };
    }

    public <T extends Comparable<? super T>> Specification<E> filterGreaterThan(T value, String field) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                throw new IllegalArgumentException("Invalid input on filter lower than.");
            }

            return criteriaBuilder.greaterThan(root.get(field), value);
        };
    }

    public Specification<E> dynamicFilterLike(Map<String, Object> attributes) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicates = criteriaBuilder.conjunction();

            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value != null) {
                    String likePattern = "%" + value.toString().toLowerCase(Locale.ROOT) + "%";
                    predicates = criteriaBuilder.and(predicates, criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(key).as(String.class)), likePattern));
                }
            }

            return predicates;
        };
    }

}
