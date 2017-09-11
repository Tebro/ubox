package com.oldbox.ubox.repository;

import com.oldbox.ubox.entity.AuthToken;
import com.oldbox.ubox.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
    public User findByTokensContains(AuthToken token);


}
