package example.Simple.Shop.model.mark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkDto {
    private long productId;
    private long authorId;
    private Integer value;
}
