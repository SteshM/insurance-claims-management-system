package com.skills.insuranceclaimsmanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "users")
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
        @ManyToMany
        @JoinTable(
                name = "user_role",
                joinColumns = @JoinColumn(
                        name = "user_id", referencedColumnName = "id"
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "role_id", referencedColumnName = "id"
                )
        )
        private List<Roles> roles;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return authoritiesInAllRoles().stream().map(SimpleGrantedAuthority::new).toList();
        }

        public List<String> authoritiesInAllRoles(){
                List<String> authorities = new ArrayList<>();
                for(Roles role : roles){
                        for(Authority authority : role.getAuthorities()){
                               authorities.add(authority.name());
                        }
                }
                return authorities;
        }
}
