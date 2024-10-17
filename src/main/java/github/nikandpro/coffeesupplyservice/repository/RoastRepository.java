package github.nikandpro.coffeesupplyservice.repository;

import github.nikandpro.coffeesupplyservice.entity.Roast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoastRepository extends JpaRepository<Roast, Integer> {

    @Query(nativeQuery = true, value = """
                SELECT r FROM roasting r JOIN country_stats c ON r.country_id = c.id
                WHERE c.country IN :countries
            """)
    List<Roast> findByCountryStats(@Param("countries") List<String> countries);
}
