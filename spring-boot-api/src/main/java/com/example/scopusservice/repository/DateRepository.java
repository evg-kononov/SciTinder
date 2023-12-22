package com.example.scopusservice.repository;

import com.example.scopusservice.model.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {

}
