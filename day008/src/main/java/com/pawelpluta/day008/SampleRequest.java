package com.pawelpluta.day008;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

record SampleRequest(
        @PositiveOrZero @NotNull Integer integer,
        @NotNull @Negative BigDecimal decimal,
        @NotNull @Size(min = 8, max = 16) String text,
        @Valid NestedRequestData data) {

    @MagicLogicValidation
    record NestedRequestData(
            Boolean magicLogicFlag,
            String magicLogicField) {
    }
}
