package com.app.demo.repository;

import com.app.demo.entity.OrderHasParts;
import com.app.demo.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderHasPartsRepository extends JpaRepository<OrderHasParts,Integer> {
    @Query("SELECT op.parts FROM OrderHasParts op WHERE op.orders.id = :orderId")
    List<Part> findByOrdersId(Integer orderId);


    OrderHasParts findByOrdersIdAndPartsPartNum(Integer orderId,String partNum);




    @Query("SELECT op.parts FROM OrderHasParts op WHERE op.orders.id = :orderId")
    List<Part> findPartsByOrderId(Integer orderId);

    @Query("SELECT SUM(ohp.quantity * CAST(ohp.parts.partPrice AS int)) FROM OrderHasParts ohp WHERE ohp.orders.id = :orderId")
    Integer sumPartCostByOrdersId(Integer orderId);





















}




