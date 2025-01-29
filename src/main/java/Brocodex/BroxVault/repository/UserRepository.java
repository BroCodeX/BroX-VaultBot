package Brocodex.BroxVault.repository;

import Brocodex.BroxVault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTelegramId(Long telegramId);
    void deleteByTelegramId(Long telegramId);
}
