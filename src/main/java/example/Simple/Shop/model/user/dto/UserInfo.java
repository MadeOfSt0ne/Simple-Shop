package example.Simple.Shop.model.user.dto;

import example.Simple.Shop.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    private long id;
    private String username;
    private String email;
    private Role role;
    private BigDecimal balance;
    private boolean isBlocked;
    private List<Long> organizationsIds;

}
