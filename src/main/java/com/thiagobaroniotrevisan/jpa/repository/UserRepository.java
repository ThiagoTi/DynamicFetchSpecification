package com.thiagobaroniotrevisan.jpa.repository;

import com.thiagobaroniotrevisan.jpa.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MyRepository<User, Long> {

}
