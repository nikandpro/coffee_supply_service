package github.nikandpro.coffeesupplyservice.repository;

import github.nikandpro.coffeesupplyservice.entity.CountryStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryStatsRepository extends JpaRepository<CountryStats, Integer> {
}
