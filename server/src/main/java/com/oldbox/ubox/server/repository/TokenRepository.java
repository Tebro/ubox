package com.oldbox.ubox.server.repository;

import com.oldbox.ubox.server.entity.AuthToken;
import com.oldbox.ubox.server.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<AuthToken, Long> {

    public AuthToken findAuthTokenByToken(String token);

    public void deleteAllByUser(User user);

}
