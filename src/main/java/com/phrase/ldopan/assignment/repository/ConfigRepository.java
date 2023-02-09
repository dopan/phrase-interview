package com.phrase.ldopan.assignment.repository;

import com.phrase.ldopan.assignment.model.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ConfigRepository extends CrudRepository<Config, Long> {

}