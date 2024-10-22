package github.nikandpro.coffeesupplyservice.repository;

import github.nikandpro.coffeesupplyservice.entity.Roast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoastRepository extends JpaRepository<Roast, Integer> {

    @Query(nativeQuery = true, value = """
                SELECT SUM(ri.weight_out) FROM roasting ri
                WHERE ri.grain_id IN :grains
            """)
    Integer findSumByGrainId(@Param("grains") List<Long> grainsId);

    @Query(nativeQuery = true, value = """
                SELECT r FROM roasting r
                WHERE r.brigade_id IN :n
            """)
    List<Roast> findAllGrainByBrigades(@Param("n") List<UUID> brigadesId);

    @Query(nativeQuery = true, value = """
                SELECT r FROM roasting r
                WHERE r.country_id IN :n
            """)
    List<Roast> findAllGrainByCountryStats(@Param("n") List<Long> countries);
}
