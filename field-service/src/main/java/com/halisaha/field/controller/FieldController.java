package com.halisaha.field.controller;

import com.halisaha.field.model.Field;
import com.halisaha.field.model.FieldType;
import com.halisaha.field.service.FieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @GetMapping
    public ResponseEntity<List<Field>> getAllFields() {
        return ResponseEntity.ok(fieldService.getAllFields());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Field>> getActiveFields() {
        return ResponseEntity.ok(fieldService.getActiveFields());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Field>> getFieldsByType(@PathVariable FieldType type) {
        return ResponseEntity.ok(fieldService.getFieldsByType(type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldService.getFieldById(id));
    }

    @PostMapping
    public ResponseEntity<Field> createField(@Valid @RequestBody Field field) {
        return ResponseEntity.ok(fieldService.createField(field));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable Long id, @Valid @RequestBody Field field) {
        return ResponseEntity.ok(fieldService.updateField(id, field));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return ResponseEntity.ok().build();
    }
} 