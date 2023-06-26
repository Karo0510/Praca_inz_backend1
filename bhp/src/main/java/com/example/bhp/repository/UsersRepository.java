package com.example.bhp.repository;

import com.example.bhp.auth.Users;
import com.example.bhp.entity.TrainingRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UsersRepository extends JpaRepository<Users, Long>
{
    @Query("select u from Users u where u.email = ?1 and u.password = ?2")
    public Optional<Users> findOneByEmailAndPassword(String email, String password);
    @Query("select u from Users u where u.email = ?1")
    public Users findByEmail(String email);
}
