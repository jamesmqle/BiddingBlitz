package com.example.BiddingBlitz.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    private Long userId;

    private String firstName;
    private String lastName;

    // One-to-many relationship with UserAddress (one user can have multiple addresses)
    @OneToMany(mappedBy = "userInfo")
    private Set<UserAddress> addresses;  // A set of UserAddress entities for each user

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<UserAddress> addresses) {
        this.addresses = addresses;
    }
}
