package br.com.treinaweb.cleancodesolid.validators;

import org.springframework.stereotype.Component;

import br.com.treinaweb.cleancodesolid.exceptions.ValidacaoException;
import br.com.treinaweb.cleancodesolid.models.Livro;
import br.com.treinaweb.cleancodesolid.repositories.LivroRepository;

@Component
public class LivroValidator implements Validator<Livro> {

    private LivroRepository livroRepository;

    public LivroValidator(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public void validar(Livro model) {
        validaTituloIsNotNull(model);
    }

    private void validaTituloIsNotNull(Livro model) {
        if (model.getTitulo() == null) {
            throw new ValidacaoException("O título não pode ser nulo");
        }

        validaTituloIsNotEmpty(model);
    }

    private void validaTituloIsNotEmpty(Livro model) {
        if (model.getTitulo().isEmpty()) {
            throw new ValidacaoException("O título não pode estar em branco");
        }

        validaTituloIsMaiorQue3Caracteres(model);
    }

    private void validaTituloIsMaiorQue3Caracteres(Livro model) {
        if (model.getTitulo().length() < 3) {
            throw new ValidacaoException("O título não pode ter menos que 3 caracteres");
        }

        validaTituloIsMenorQue255Caracteres(model);
    }

    private void validaTituloIsMenorQue255Caracteres(Livro model) {
        if (model.getTitulo().length() > 255) {
            throw new ValidacaoException("O título não pode ter mais que 255 caracteres");
        }

        validaTituloJaCadastrado(model);
    }

    private void validaTituloJaCadastrado(Livro model) {
        if (livroRepository.isTituloExists(model.getTitulo())) {
            throw new ValidacaoException("Já existe um livro cadastrado com esse título");
        }

        validaIsbnHas10Caracteres(model);
    }

    private void validaIsbnHas10Caracteres(Livro model) {
        if (model.getIsbn() != null && model.getIsbn().length() != 10) {
            throw new ValidacaoException("O ISBN deve ter 10 caracteres");
        }
    }

}
