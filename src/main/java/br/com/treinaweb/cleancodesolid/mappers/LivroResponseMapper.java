package br.com.treinaweb.cleancodesolid.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.cleancodesolid.dtos.outputs.LivroResponse;
import br.com.treinaweb.cleancodesolid.models.Livro;

@Component
public class LivroResponseMapper implements ResponseMapper<LivroResponse, Livro> {

    @Override
    public LivroResponse toResponse(Livro model) {
        LivroResponse response = new LivroResponse();
        response.setId(model.getId());
        response.setTitulo(model.getTitulo());
        response.setAutor(model.getAutor());

        return response;
    }

}
