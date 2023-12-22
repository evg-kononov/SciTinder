package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("select o from Organization o left join fetch o.country left join fetch o.sector where o.name = ?1")
    List<Organization> findAllByName(String name);
}
