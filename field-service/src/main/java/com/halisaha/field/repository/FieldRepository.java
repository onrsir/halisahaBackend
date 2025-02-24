package com.halisaha.field.repository;

import com.halisaha.field.model.Field;
import com.halisaha.field.model.FieldType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByType(FieldType type);
    List<Field> findByIsActiveTrue();
    boolean existsByName(String name);
} 