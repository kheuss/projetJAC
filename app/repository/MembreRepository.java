package repository;

import com.google.common.base.Preconditions;
import models.Membre;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MembreRepository {

    private final Map<String, Membre> repo;
    private final SessionRepository sessionRepository;
    private final String salt;

    public static final MembreRepository instance = new MembreRepository();

    private MembreRepository() {
        this.repo = new HashMap<>();
        this.salt = BCrypt.gensalt();
        this.sessionRepository = SessionRepository.instance;
    }

    public Membre create(String email, String password) {
        final Optional<Membre> maybeUser = Optional.ofNullable(repo.get(email));
        Preconditions.checkArgument(!maybeUser.isPresent(), "duplicated username");

        final Membre newMembre = new Membre();
        newMembre.email=email;
        newMembre.adresse=hash(password);
        repo.put(email, newMembre);
        return newMembre;
    }

    public boolean login(String email, String password) {
        return Optional.ofNullable(repo.get(email))
                .map(u -> {
                    boolean validPassword = compare(password, u.motDePasse);
                    if (validPassword) sessionRepository.login(u);
                    return validPassword;
                }).orElse(false);
    }

    public void logout(String email) {
        this.sessionRepository.logout(email);
    }

    public boolean isLoggedIn(String email) { return this.sessionRepository.isLoggedIn(email); }

    public String hash(String value) {
        return BCrypt.hashpw(value, salt);
    }

    private boolean compare(String password, String hashed) {
        return hash(password).equals(hashed);
    }

}

