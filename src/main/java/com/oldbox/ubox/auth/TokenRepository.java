package com.oldbox.ubox.auth;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<AuthToken, Long> {

    public AuthToken findAuthTokenByToken(String token);

}
