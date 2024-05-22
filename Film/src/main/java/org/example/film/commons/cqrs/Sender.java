package org.example.film.commons.cqrs;

import org.reflections.Reflections;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class Sender implements ISender{
    private final Map<Class<?>, Class<? extends IRequestHandler<?,?>>> handlers = new HashMap<>();      //Declare Map to map Request class to Request Handler. Use generic types to ensure type safe
    private final ConfigurableApplicationContext context;

    public Sender(ConfigurableApplicationContext context) {
        this.context = context;
        Reflections reflections = new Reflections("org.example.film");                            //reflections is used to scan the classpath and collect metadata about classes, methods, fields, annotations,...-> It scans class in package and sub-package "org.example.film"
        Set<Class<? extends IRequestHandler>> classes = reflections.getSubTypesOf(IRequestHandler.class);
        for (Class<? extends IRequestHandler> clazz : classes) {
            Type[] interfaces = clazz.getGenericInterfaces();
            if (interfaces.length > 0 && interfaces[0] instanceof ParameterizedType) {
                Type requestType = ((ParameterizedType) interfaces[0]).getActualTypeArguments()[0];
                handlers.put((Class<?>) requestType, (Class<? extends IRequestHandler<?, ?>>) clazz);
            }
        }
    }

    @Override
    public <TResponse> HandleResponse<TResponse> send(IRequest<TResponse> request) {
        IRequestHandler<IRequest<TResponse>, TResponse> handler = (IRequestHandler<IRequest<TResponse>, TResponse>) getHandler(request.getClass());
        try{
            return handler.handle(request);
        } catch (Exception e){
            throw new RuntimeException("Handle execution failed for request : " + request.getClass().getName(), e);
        }
    }

    private IRequestHandler<?, ?> getHandler(Class<?> clazz){
        Class<? extends IRequestHandler<?,?>> handlerClass = handlers.get(clazz);
        if(handlerClass == null){
            throw new RuntimeException("No handler found for : " + clazz.getName());
        }
        return context.getBean(handlerClass);
    }
}