package se.mindstar.artclub;

import org.springframework.data.repository.CrudRepository;
import se.mindstar.artclub.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

