package github.nikandpro.coffeesupplyservice.repository;

import github.nikandpro.coffeesupplyservice.entity.Grain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrainRepository extends JpaRepository<Grain,Integer> {

    @Query(nativeQuery = true, value = """
        SELECT g FROM grain g JOIN country_stats c ON g.origin_country = c.id
        WHERE c.country IN :countries
    """)
    List<Grain> findByCountryStats(@Param("countries") List<String> countries);

    @Query(nativeQuery = true, value = """
        SELECT g FROM grain g 
        WHERE g.grain_type IN :types
    """)
    List<Grain> findByGrainType(@Param("types") List<String> types);
}
