package com.oldbox.ubox.server.repository;

import com.oldbox.ubox.server.entity.ProfileFieldValue;
import com.oldbox.ubox.server.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProfileFieldValueRepository extends CrudRepository<ProfileFieldValue, Long>{
}
