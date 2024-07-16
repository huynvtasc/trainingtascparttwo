package org.ngvhuy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "dataset")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "fields_list")
    private String fields;
    @Column(name = "is_range_input")
    private boolean rangeInput;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private Instant updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dataset")
    private List<DatasetValue> datasetValues;
}
