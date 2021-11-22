package br.com.treinaweb.cleancodesolid.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinaweb.cleancodesolid.dtos.inputs.LivroRequest;
import br.com.treinaweb.cleancodesolid.dtos.outputs.LivroResponse;
import br.com.treinaweb.cleancodesolid.services.LivroService;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroRestController {

    private LivroService livroService;

    public LivroRestController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public LivroResponse cadastrar(@RequestBody LivroRequest livroRequest) {
        return this.livroService.cadastrar(livroRequest);
    }

    @GetMapping
    public List<LivroResponse> listar() {
        return livroService.listar();
    }

}
