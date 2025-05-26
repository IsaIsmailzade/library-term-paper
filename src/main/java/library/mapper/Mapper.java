package library.mapper;

public interface Mapper<T, F> {

    F mapFrom(T t);
}
