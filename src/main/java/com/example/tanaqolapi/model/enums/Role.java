package com.example.tanaqolapi.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    CUSTOMER(
        Set.of(Permission.CAN_REGISTER,
            Permission.CAN_LOGIN,
            Permission.CAN_RECEIVE_NOTIFICATIONS,
            Permission.CAN_VIEW_AVAILABLE_VEHICLES,
            Permission.CAN_BOOK_RIDE,
            Permission.CAN_TRACK_RIDE,
            Permission.CAN_CANCEL_RIDE,
            Permission.CAN_MAKE_PAYMENT,
            Permission.CAN_RATE_DRIVER)
    ),


    DRIVER(
        Set.of(Permission.CAN_REGISTER,
            Permission.CAN_LOGIN,
            Permission.CAN_RECEIVE_NOTIFICATIONS,
            Permission.CAN_SET_AVAILABILITY,
            Permission.CAN_VIEW_ASSIGNED_RIDES,
            Permission.CAN_ACCEPT_RIDE_REQUEST,
            Permission.CAN_REJECT_RIDE_REQUEST,
            Permission.CAN_START_RIDE,
            Permission.CAN_COMPLETE_RIDE)
    ),

    ADMIN(
        Set.of(Permission.CAN_LOGIN,
            Permission.CAN_RECEIVE_NOTIFICATIONS,
            Permission.CAN_MANAGE_USERS,
            Permission.CAN_MANAGE_VEHICLES,
            Permission.CAN_VIEW_RIDES,
            Permission.CAN_VIEW_NOTIFICATIONS,
            Permission.CAN_GENERATE_REPORTS,
            Permission.CAN_MONITOR_SYSTEM)
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities =getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}
