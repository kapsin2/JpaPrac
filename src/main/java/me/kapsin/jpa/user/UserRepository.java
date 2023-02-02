package me.kapsin.jpa.user;

import java.util.Optional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository {

  Optional<User> findByUsername(String userName);

  //findByPassword 막기위해 기능을 제한
}