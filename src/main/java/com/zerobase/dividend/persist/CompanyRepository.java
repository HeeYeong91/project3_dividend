package com.zerobase.dividend.persist;

import com.zerobase.dividend.persist.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회사 레파지토리
 * @author 이희영
 */
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    boolean existsByTicker(String ticker);

    Optional<CompanyEntity> findByName(String name);
}
