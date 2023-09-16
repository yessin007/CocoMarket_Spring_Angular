package com.example.coco_spring.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Integer codeReset;
    private String address;
    private Integer zipCode;
    private String city;
    private String country;
    @Temporal(TemporalType.DATE)
    private Date dayOfBirth;
    private String cin;
    private String telNum;
    private Boolean expired;
    @Temporal(TemporalType.DATE)
    private Date dateToUnexired;
    private Boolean locked;
    private Integer codeActivation;
    @Enumerated(EnumType.STRING)
    private Role roles;
    @JsonIgnore
    @OneToOne
    Payement payement;
    @JsonIgnore
    @OneToOne
    Cart cart;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Review> reviews;


    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Token> tokens;

    @OneToOne
    ClientLocation clientLocation;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProviderRating> providerRatings;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    Set<PostStore> postStores;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<PostLike> postLikes;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<PostDislike> postDislikes;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<PostComment> postComments;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Answer> answers;

    @OneToMany(cascade = CascadeType.ALL)
    Set<StoreCatalogLike> storeCatalogLikes;

    @JsonIgnore
    @ManyToMany
    List<StoreCatalog> favories;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    List<LikeDislikeProduct> likeDislikeProductList;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Subscription> subscriptions;
}