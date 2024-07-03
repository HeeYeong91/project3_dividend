package com.zerobase.dividend.persist.entity;

import com.zerobase.dividend.model.Dividend;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 배당 엔티티
 * @author 이희영
 */
@Entity(name = "DIVIDEND")
@Getter
@NoArgsConstructor
@ToString
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = { "companyId", "date" }
                )
        }
)
public class DividendEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private LocalDateTime date;

    private String dividend;

    public DividendEntity(Long companyId, Dividend dividend) {
        this.companyId = companyId;
        this.date = dividend.getDate();
        this.dividend = dividend.getDividend();
    }
}
