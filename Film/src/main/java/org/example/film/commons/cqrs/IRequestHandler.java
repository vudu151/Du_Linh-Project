package org.example.film.commons.cqrs;

public interface IRequestHandler<TRequest, TResponse> {
    HandleResponse<TResponse> handle(TRequest request) throws Exception;
}
