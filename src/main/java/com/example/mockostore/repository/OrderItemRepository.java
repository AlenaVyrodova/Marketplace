package com.example.mockostore.repository;

import com.example.mockostore.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long orderId, Pageable pageable);

    Optional<OrderItem> findByIdAndOrderId(Long itemId, Long orderId);
}