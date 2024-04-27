package com.zenyatta.nttdata.challenge.database.h2.usecase.price.get;

import com.zenyatta.nttdata.challenge.database.h2.domain.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface GetPriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p " +
            "WHERE :date BETWEEN p.dateStart AND p.dateEnd " +
            "AND p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "ORDER BY p.priority DESC " +
            "LIMIT 1")
    Optional<PriceEntity> findHighestPriorityPrice(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("date") LocalDateTime date);
            
}
