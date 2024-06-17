package com.mju_lion.letter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Term extends BaseEntity{
    @Column(nullable = false, length = 100, unique = true, name = "organization_name")
    private String title;

    @Column(nullable = false, length = 100, name = "organization_introduction")
    private String terms;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "terms")
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "term")
    private List<UserTerm> userTerms;
}
