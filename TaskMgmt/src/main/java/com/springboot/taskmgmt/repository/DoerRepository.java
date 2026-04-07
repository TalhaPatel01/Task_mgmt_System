package com.springboot.taskmgmt.repository;

import com.springboot.taskmgmt.model.Doer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoerRepository extends JpaRepository<Doer,Long> {
}