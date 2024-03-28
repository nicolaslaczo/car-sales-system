package com.app.demo.repository;

import com.app.demo.entity.OrderHasParts;
import com.app.demo.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderHasPartsRepository extends JpaRepository<OrderHasParts,Integer> {
    @Query("SELECT op.parts FROM OrderHasParts op WHERE op.orders.id = :orderId")
    List<Part> findByOrdersId(Integer orderId);

    List<Part> findAllPartsByOrdersId(Integer orderId);






















}




