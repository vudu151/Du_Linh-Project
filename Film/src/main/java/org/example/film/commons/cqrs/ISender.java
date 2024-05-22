package org.example.film.commons.cqrs;

public interface ISender {
    <TResponse> HandleResponse<TResponse> send(IRequest<TResponse> request);
}
