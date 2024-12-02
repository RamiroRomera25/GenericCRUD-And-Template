package rami.generic.dtos.dummy;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DummyDtoFilter {
    private Long id;
    private String dummy;
    private Boolean isActive;
}
