package com.oldbox.ubox.server.repository;

import com.oldbox.ubox.server.entity.AuthToken;
import com.oldbox.ubox.server.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
    public User findByTokensContains(AuthToken token);


}
