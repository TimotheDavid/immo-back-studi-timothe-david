package infoco.immo.database.SQL.user;

import infoco.immo.core.User;

import java.util.UUID;

public interface UserRepositoryI {

    public void create(User user);
    public User get(UUID userId);
    public User getByToken(String token);

    public User  login(User user);
}
