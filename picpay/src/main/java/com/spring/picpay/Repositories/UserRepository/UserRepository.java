package com.spring.picpay.Repositories.UserRepository;

import com.spring.picpay.Models.UserModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
