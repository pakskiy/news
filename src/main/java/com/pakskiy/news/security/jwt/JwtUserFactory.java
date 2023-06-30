package com.pakskiy.news.security.jwt;

import com.pakskiy.news.model.UserEntity;
import com.pakskiy.news.model.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }
    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUserStatus()==UserStatus.ACTIVE,
                user.getUserStatus(),
                user.getUpdatedAt(),
                mapToGrantedAuthorities(user.getRoleName().name())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String userRoles) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(userRoles));
        return grantedAuthorityList;
    }


}
