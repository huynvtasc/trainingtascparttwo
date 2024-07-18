package org.ngvhuy.SpringDataJpaWithCache.repository;

import org.ngvhuy.SpringDataJpaWithCache.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset, Long> {
}
