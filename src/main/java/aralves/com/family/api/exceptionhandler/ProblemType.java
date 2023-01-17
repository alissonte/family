package aralves.com.family.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    INVALID_DATA("/invalid-data", "Dados inválidos"),
    ERROR_SYSTEM("/error-system", "Erro de sistema"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Recurso não encontrado"),
    INVALID_PARAM("/invalid-param", "Parâmetro inválido");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "http://aralves-tech.com.br" + path;
        this.title = title;
    }

}