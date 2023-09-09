package com.codingdock.restfulapi.repos;
import com.codingdock.restfulapi.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
