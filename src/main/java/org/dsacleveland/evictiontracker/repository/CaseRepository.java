package org.dsacleveland.evictiontracker.repository;

import org.dsacleveland.evictiontracker.model.evictiondata.entity.CaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, UUID>,
        PagingAndSortingRepository<CaseEntity, UUID> {

    @Query("select c from CaseEntity c join fetch c.property p where p.neighborhood = :neighborhood")
    List<CaseEntity> findAllByNeighborhood(String neighborhood);

    @EntityGraph(attributePaths = "property.neighborhood")
    @Query("from CaseEntity c")
    Page<CaseEntity> findAllByNeighborhood(String neighborhood, Pageable pageable);
}
