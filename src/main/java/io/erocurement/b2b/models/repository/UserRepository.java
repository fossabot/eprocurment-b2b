package io.erocurement.b2b.models.repository;



import io.erocurement.b2b.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByUsername(String username);

    @Query("select u from User u where u.username=?1")
    public User findByUsername2(String username);

    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);

    public boolean existsByUsernameOrEmail(String username, String email);
}
