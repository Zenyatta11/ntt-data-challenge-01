package com.zenyatta.nttdata.challenge.database.h2.domain;

import static lombok.AccessLevel.PRIVATE;

import com.zenyatta.nttdata.challenge.core.domain.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@Entity
@Table(name = "prices")
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "entry_id", unique = true, nullable = false)
    private Long entryId;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime dateStart;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime dateEnd;

    @Column(name = "price_list", nullable = false)
    private Long priceListId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    public Long getId() {
        return this.entryId;
    }
}
