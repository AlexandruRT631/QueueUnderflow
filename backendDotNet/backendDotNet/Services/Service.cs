namespace backendDotNet.Services;

public interface IService<T>
{
    public List<T> GetAll();
    public T? GetById(long id);
    public string DeleteById(long id);
}