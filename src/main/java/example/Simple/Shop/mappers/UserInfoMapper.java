package example.Simple.Shop.mappers;

import example.Simple.Shop.model.organization.Organization;
import example.Simple.Shop.model.user.User;
import example.Simple.Shop.model.user.dto.UserInfo;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UserInfoMapper {

    public static UserInfo toUserInfo(User user) {

        List<Long> orgs = user.getOrganizations().stream()
                .map(Organization::getId)
                .toList();

        return UserInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .balance(user.getBalance())
                .isBlocked(user.isBlocked())
                .organizationsIds(orgs)
                .build();
    }

}
