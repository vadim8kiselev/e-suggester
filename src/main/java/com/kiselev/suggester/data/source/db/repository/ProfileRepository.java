package com.kiselev.suggester.data.source.db.repository;

import com.kiselev.suggester.data.model.entity.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, String> {
}
