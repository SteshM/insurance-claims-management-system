package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Table(name = "users")
@Slf4j
public class Users implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;
        private String fullName;
        private String phoneNumber;
        @Column(unique = true, nullable = false)
        private String email;
        private Date dateOfBirth;
        private String gender;
        private Boolean active = true;
        private Date dateCreated;
        private String createdBy;
        private Date dateModified ;
        private String modifiedBy;
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_role",
                joinColumns = @JoinColumn(
                        name = "user_id", referencedColumnName = "id"
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"
                )
        )
        private Collection<Roles> roles;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                log.info("Authorities in roles = {}",authoritiesInAllRoles());

                return authoritiesInAllRoles().stream().map(SimpleGrantedAuthority::new).toList();
        }

        public Set<String> authoritiesInAllRoles() {

                return this.roles.stream()
                        .map(Roles::getName) // Stream over authorities of each role
                    //    .map(Authority::getAuthorityName) // Use Enum::name if Authority is an enum
                        .collect(Collectors.toSet()); // Collect into a set to ensure uniqueness
        }


}
