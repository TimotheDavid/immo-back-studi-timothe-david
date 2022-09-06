package infoco.immo.usecase.user;

import infoco.immo.core.User;

import java.util.UUID;

public interface UserUseCaseI {
    public void create(User user);
    public User get(UUID userId);
    public User getByToken(String token);
}
