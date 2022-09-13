package infoco.immo.database.SQL.authentication;

import infoco.immo.core.Authentication;

public interface AuthenticationRepositoryI {

    public void create(Authentication authentication);

    public Authentication get(Authentication authentication);

    Authentication getByToken(String token);
}
