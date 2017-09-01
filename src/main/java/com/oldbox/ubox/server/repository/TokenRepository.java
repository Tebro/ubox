package com.oldbox.ubox.server.repository;

import com.oldbox.ubox.server.entity.AuthToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<AuthToken, Long> {

    public AuthToken findAuthTokenByToken(String token);

}
