package sk.appsmanager.service.validator;

public abstract class AbstractValidator<E> implements Validator<E> {

    @Override
    public void validateId(Integer id) {
        if (id != null && id < 1) {
            throw new IllegalArgumentException("id is less than 1");
        }
    }
}
