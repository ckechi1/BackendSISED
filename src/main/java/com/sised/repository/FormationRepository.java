package com.sised.repository;

import com.sised.model.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormationRepository extends JpaRepository<Formation , Long> {
    Page<Formation> findByPostId(Long postId, Pageable pageable);
    Optional<Formation> findByIdAndPostId(Long id, Long postId);
}
