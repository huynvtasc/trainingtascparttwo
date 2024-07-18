package org.ngvhuy.SpringDataJpaWithCache.repository;

import org.ngvhuy.SpringDataJpaWithCache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
