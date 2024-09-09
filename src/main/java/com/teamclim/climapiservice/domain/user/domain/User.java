package com.teamclim.climapiservice.domain.user.domain;

import com.teamclim.climapiservice.domain.user.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private Long id;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String profile_image_path;

    private String banner_image_path;

    private Timestamp created_at;

    private Timestamp updated_at;

    private Role role;

    public void updateMyInfo(String user_name, String password, String email, Timestamp updated_at) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.updated_at = updated_at;
    }
}
