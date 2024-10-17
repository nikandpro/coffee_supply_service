package github.nikandpro.coffeesupplyservice.repository;

import github.nikandpro.coffeesupplyservice.entity.Grain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrainRepository extends JpaRepository<Grain,Integer> {
}
