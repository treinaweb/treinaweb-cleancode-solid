package br.com.treinaweb.cleancodesolid.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.treinaweb.cleancodesolid.dtos.inputs.LivroRequest;
import br.com.treinaweb.cleancodesolid.dtos.outputs.LivroResponse;
import br.com.treinaweb.cleancodesolid.exceptions.ValidacaoException;
import br.com.treinaweb.cleancodesolid.mappers.RequestMapper;
import br.com.treinaweb.cleancodesolid.mappers.ResponseMapper;
import br.com.treinaweb.cleancodesolid.models.Livro;
import br.com.treinaweb.cleancodesolid.repositories.LivroRepository;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    private ResponseMapper<LivroResponse, Livro> responseMapper;

    private RequestMapper<LivroRequest, Livro> requestMapper;

    public LivroService(
        LivroRepository livroRepository,
        ResponseMapper<LivroResponse, Livro> responseMapper,
        RequestMapper<LivroRequest, Livro> requestMapper
    ) {
        this.livroRepository = livroRepository;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    public LivroResponse cadastrar(LivroRequest livroRequest) {
        Livro livro = requestMapper.toModel(livroRequest);

        validarLivro(livro);

        livroRepository.save(livro);

        return responseMapper.toResponse(livro);
    }

    public List<LivroResponse> listar() {
        List<Livro> livros = livroRepository.findAll();

        return livroListToLivroResponseList(livros);
    }

    private void validarLivro(Livro livro) {
        if (livro.getTitulo() == null) {
            throw new ValidacaoException("O título não pode ser nulo");
        }

        if (livro.getTitulo().isEmpty()) {
            throw new ValidacaoException("O título não pode estar em branco");
        }

        if (livro.getTitulo().length() < 3) {
            throw new ValidacaoException("O título não pode ter menos que 3 caracteres");
        }

        if (livro.getTitulo().length() > 255) {
            throw new ValidacaoException("O título não pode ter mais que 255 caracteres");
        }

        if (livroRepository.isTituloExists(livro.getTitulo())) {
            throw new ValidacaoException("Já existe um livro cadastrado com esse título");
        }

        if (livro.getIsbn() != null && livro.getIsbn().length() != 10) {
            throw new ValidacaoException("O ISBN deve ter 10 caracteres");
        }
    }

    private List<LivroResponse> livroListToLivroResponseList(List<Livro> livros) {
        List<LivroResponse> livroResponseList = new ArrayList<>();
        for (Livro livro : livros) {
            livroResponseList.add(responseMapper.toResponse(livro));
        }

        return livroResponseList;
    }

}
