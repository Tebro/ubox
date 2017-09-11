package com.oldbox.ubox.user;

import com.oldbox.ubox.auth.AuthToken;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
    public User findByTokensContains(AuthToken token);


}
