package com.senla.finalTask.repository;

import com.senla.finalTask.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
    Optional<User> findById(String s);

    User findByUsername(String username);

    Page<User> findByUsername(String username, Pageable pageable);

    User findByActivationCode(String code);

    void deleteById(Long id);

    List<User> findById(Long id);
}
