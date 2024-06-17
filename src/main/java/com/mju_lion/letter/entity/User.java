package com.mju_lion.letter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(length = 100, nullable = false, unique = true)
    private String userId;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Board> boards;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserTerm> userTerms;
}
