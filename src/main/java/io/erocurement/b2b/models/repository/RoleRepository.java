package io.erocurement.b2b.models.repository;



import io.erocurement.b2b.models.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    public List<Role> findAll();
}
