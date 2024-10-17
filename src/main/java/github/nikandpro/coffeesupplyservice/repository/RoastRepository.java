package github.nikandpro.coffeesupplyservice.repository;

import github.nikandpro.coffeesupplyservice.entity.Roast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoastRepository extends JpaRepository<Roast, Integer> {
}
