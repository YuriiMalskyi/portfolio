package com.malskyi.project.entity;

import com.malskyi.project.entity.enums.Roles;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(indexes = @Index(columnList = "userId"))
@Table(name = "users")
public class UserEntity extends BaseEntity {

//		@Column(unique = true)
//		private String userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Roles role;

}
