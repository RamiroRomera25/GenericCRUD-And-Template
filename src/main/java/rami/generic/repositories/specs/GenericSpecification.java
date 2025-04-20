package rami.generic.repositories.specs;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Specification to filter any entity by created date.
 * @param <E> entity that extends from base entity
 */
@NoArgsConstructor
@Component
public class GenericSpecification<E> {

    /**
     * Gets the path for a field name, supporting nested properties with dot notation.
     * @param root the root entity
     * @param fieldName the field name, which may contain dots for nested properties
     * @return the Path object for the field
     */
    private Path<?> getPath(Path<?> root, String fieldName) {
        if (fieldName.contains(".")) {
            String[] nestedFields = fieldName.split("\\.");
            Path<?> path = root.get(nestedFields[0]);

            for (int i = 1; i < nestedFields.length; i++) {
                path = path.get(nestedFields[i]);
            }

            return path;
        } else {
            return root.get(fieldName);
        }
    }

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
                    Path<?> path = getPath(root, key);
                    predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(path, value));
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
                    Path<?> path = getPath(root, field);
                    orPredicates = criteriaBuilder.or(
                            orPredicates,
                            criteriaBuilder.like(criteriaBuilder.lower(path.as(String.class)), likePattern)
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

            Path<T> path = (Path<T>) getPath(root, field);
            return criteriaBuilder.between(path, lower, higher);
        };
    }

    public <T extends Comparable<? super T>> Specification<E> filterLowerThan(T value, String field) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                throw new IllegalArgumentException("Invalid input on filter lower than.");
            }

            Path<T> path = (Path<T>) getPath(root, field);
            return criteriaBuilder.lessThan(path, value);
        };
    }

    public <T extends Comparable<? super T>> Specification<E> filterGreaterThan(T value, String field) {
        return (root, query, criteriaBuilder) -> {
            if (value == null) {
                throw new IllegalArgumentException("Invalid input on filter lower than.");
            }

            Path<T> path = (Path<T>) getPath(root, field);
            return criteriaBuilder.greaterThan(path, value);
        };
    }

    public Specification<E> dynamicFilterLike(Map<String, Object> attributes) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicates = criteriaBuilder.conjunction();

            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value != null) {
                    Path<?> path = getPath(root, key);
                    String likePattern = "%" + value.toString().toLowerCase(Locale.ROOT) + "%";
                    predicates = criteriaBuilder.and(predicates, criteriaBuilder.like(
                            criteriaBuilder.lower(path.as(String.class)), likePattern));
                }
            }

            return predicates;
        };
    }

    public Specification<E> uniqueValue(String fieldName, Object value) {
        return this.compositeUniqueValues(Map.of(fieldName, value, "isActive", true));
    }

    public Specification<E> compositeUniqueValues(Map<String, Object> uniqueFields) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry<String, Object> entry : uniqueFields.entrySet()) {
                String fieldName = entry.getKey();
                Object value = entry.getValue();

                Path<?> path = getPath(root, fieldName);
                predicates.add(criteriaBuilder.equal(path, value));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
