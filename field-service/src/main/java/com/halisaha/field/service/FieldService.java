package com.halisaha.field.service;

import com.halisaha.field.model.Field;
import com.halisaha.field.model.FieldType;
import com.halisaha.field.repository.FieldRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public List<Field> getActiveFields() {
        return fieldRepository.findByIsActiveTrue();
    }

    public List<Field> getFieldsByType(FieldType type) {
        return fieldRepository.findByType(type);
    }

    public Field getFieldById(Long id) {
        return fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Field not found with id: " + id));
    }

    @Transactional
    public Field createField(Field field) {
        if (fieldRepository.existsByName(field.getName())) {
            throw new IllegalArgumentException("Field name already exists");
        }
        return fieldRepository.save(field);
    }

    @Transactional
    public Field updateField(Long id, Field field) {
        Field existingField = getFieldById(id);
        
        if (!existingField.getName().equals(field.getName()) && 
            fieldRepository.existsByName(field.getName())) {
            throw new IllegalArgumentException("Field name already exists");
        }

        field.setId(id);
        return fieldRepository.save(field);
    }

    @Transactional
    public void deleteField(Long id) {
        Field field = getFieldById(id);
        field.setIsActive(false);
        fieldRepository.save(field);
    }
} 