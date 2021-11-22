package br.com.treinaweb.cleancodesolid.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.treinaweb.cleancodesolid.dtos.inputs.LivroRequest;
import br.com.treinaweb.cleancodesolid.dtos.outputs.LivroResponse;
import br.com.treinaweb.cleancodesolid.mappers.RequestMapper;
import br.com.treinaweb.cleancodesolid.mappers.ResponseMapper;
import br.com.treinaweb.cleancodesolid.models.Livro;
import br.com.treinaweb.cleancodesolid.repositories.LivroRepository;
import br.com.treinaweb.cleancodesolid.validators.Validator;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    private ResponseMapper<LivroResponse, Livro> responseMapper;

    private RequestMapper<LivroRequest, Livro> requestMapper;

    private Validator<Livro> validator;

    public LivroService(
        LivroRepository livroRepository,
        ResponseMapper<LivroResponse, Livro> responseMapper,
        RequestMapper<LivroRequest, Livro> requestMapper,
        Validator<Livro> validator
    ) {
        this.livroRepository = livroRepository;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
        this.validator = validator;
    }

    public LivroResponse cadastrar(LivroRequest livroRequest) {
        Livro livro = requestMapper.toModel(livroRequest);

        validator.validar(livro);

        livroRepository.save(livro);

        return responseMapper.toResponse(livro);
    }

    public List<LivroResponse> listar() {
        List<Livro> livros = livroRepository.findAll();

        return livroListToLivroResponseList(livros);
    }

    private List<LivroResponse> livroListToLivroResponseList(List<Livro> livros) {
        List<LivroResponse> livroResponseList = new ArrayList<>();
        for (Livro livro : livros) {
            livroResponseList.add(responseMapper.toResponse(livro));
        }

        return livroResponseList;
    }

}
