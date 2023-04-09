package br.com.personal.expenses.personalexpenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.personal.expenses.personalexpenses.domain.service.TitleService;
import br.com.personal.expenses.personalexpenses.dto.Title.TitleRequestDTO;
import br.com.personal.expenses.personalexpenses.dto.Title.TitleResponseDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/titles")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping
    public ResponseEntity<List<TitleResponseDTO>> getAll() {

        return ResponseEntity.ok(titleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleResponseDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(titleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TitleResponseDTO> register(@RequestBody TitleRequestDTO title) {
        TitleResponseDTO titleResponse = titleService.register(title);
        return new ResponseEntity<>(titleResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TitleResponseDTO> update(@PathVariable Long id ,@RequestBody TitleRequestDTO Title) {

        return new ResponseEntity<>(titleService.updateById(id,Title), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        titleService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
