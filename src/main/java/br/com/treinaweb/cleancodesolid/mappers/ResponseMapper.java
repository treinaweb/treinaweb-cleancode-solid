package br.com.treinaweb.cleancodesolid.mappers;

public interface ResponseMapper<R, M> {

    R toResponse(M model);

}
