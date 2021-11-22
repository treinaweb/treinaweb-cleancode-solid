package br.com.treinaweb.cleancodesolid.mappers;

public interface RequestMapper<R, M> {

    M toModel(R request);

}
