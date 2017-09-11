package com.oldbox.ubox.repository;

import com.oldbox.ubox.entity.AuthToken;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<AuthToken, Long> {

    public AuthToken findAuthTokenByToken(String token);

}
