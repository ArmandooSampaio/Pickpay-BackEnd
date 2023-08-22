package com.spring.picpay.Models.UserModel;

import com.spring.picpay.dto.UserDto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "user_tb")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;

    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String password;
    private String firstName;
    private String lastName;

    public User(UserDto userDto){
        this.balance = userDto.balance();
        this.email = userDto.email();
        this.password = userDto.password();
        this.userType = userDto.userType();
        this.firstName = userDto.firstName();
        this.lastName = userDto.lastName();
        this.document = userDto.document();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
