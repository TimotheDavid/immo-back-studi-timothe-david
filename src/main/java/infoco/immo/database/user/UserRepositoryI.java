package infoco.immo.database.user;

import infoco.immo.core.User;

import java.util.UUID;

public interface UserRepositoryI {

    public void create(User user);
    public User get(UUID userId);
    public User getByToken(String token);
}
