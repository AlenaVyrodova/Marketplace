package com.example.mockostore.repository;

import com.example.mockostore.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems")
    List<Order> findAllByUserId(Long id, Pageable pageable);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findOrderByIdAndUserId(Long id, Long userId);
}