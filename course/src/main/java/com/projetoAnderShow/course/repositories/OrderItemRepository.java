package com.projetoAnderShow.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoAnderShow.course.entities.OrderItem;
import com.projetoAnderShow.course.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
