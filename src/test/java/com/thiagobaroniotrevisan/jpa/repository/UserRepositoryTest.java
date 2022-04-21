package com.thiagobaroniotrevisan.jpa.repository;

import com.thiagobaroniotrevisan.jpa.domain.Address;
import com.thiagobaroniotrevisan.jpa.domain.User;
import com.thiagobaroniotrevisan.jpa.domain.User_;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        User alex = new User("Alex", "pwd");
        entityManager.persistAndFlush(alex);
        entityManager.persistAndFlush(new Address(alex, "1234 John Doe Avenue"));
        entityManager.persistAndFlush(new Address(alex, "1234 Jane Doe Street"));

        User joe = new User("Joe", "pwd2");
        entityManager.persistAndFlush(joe);
        entityManager.persistAndFlush(new Address(joe, "12345 1st Avenue"));
        entityManager.persistAndFlush(new Address(joe, "12345 2nd Street"));
    }

    @Test
    void whenPageFetchJoinSpec_thenReturnException() {
        // given
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            root.fetch(User_.addresses);
            return criteriaBuilder.conjunction();
        };
        Pageable pageable = PageRequest.of(0, 1);

        // when

        // then
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> repository.findAll(spec, pageable));
    }

    @Test
    void whenPageFetchJoinCustomSpec_thenReturnUser() {
        // given
        Specification<User> spec = (root, criteriaQuery, criteriaBuilder) -> {
            root.fetch(User_.addresses);
            return criteriaBuilder.conjunction();
        };

        // when
        final List<User> users = this.repository.findAll(spec, 0, 1);

        // then
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("Alex", users.get(0).getUsername());
    }
}