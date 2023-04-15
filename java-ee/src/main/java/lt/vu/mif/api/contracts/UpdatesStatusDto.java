package lt.vu.mif.api.contracts;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatesStatusDto {
    private boolean isChecking;

    private LocalDateTime lastCheckDate;

    private Integer updateCount;
}
