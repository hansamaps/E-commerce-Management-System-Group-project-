package com.example.ClothStokePro.service.impl;

import com.example.ClothStokePro.dto.OrderitemDto;
import com.example.ClothStokePro.dto.OrderRequest;
import com.example.ClothStokePro.dto.Response;
import com.example.ClothStokePro.entity.Order;
import com.example.ClothStokePro.entity.Orderitem;
import com.example.ClothStokePro.entity.Product;
import com.example.ClothStokePro.entity.User;
import com.example.ClothStokePro.enums.OrderStatus;
import com.example.ClothStokePro.exception.NotFoundException;
import com.example.ClothStokePro.Mapper.EntityDtoMapper;
import com.example.ClothStokePro.repository.OrderitemRepo;
import com.example.ClothStokePro.repository.OrderRepo;
import com.example.ClothStokePro.repository.ProductRepo;
import com.example.ClothStokePro.service.interf.OrderitemService;
import com.example.ClothStokePro.service.interf.UserService;
import com.example.ClothStokePro.specification.OrderitemSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderitemServiceimpl implements OrderitemService {

    private final OrderRepo orderRepo;
    private final OrderitemRepo orderItemRepo;
    private final ProductRepo productRepo;
    private final UserService userService;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response placeOrder(OrderRequest orderRequest) {

        User user = userService.getLoginUser();
        // map order request items to order entities

        List<Orderitem> orderItems = orderRequest.getItems().stream().map(orderItemRequest -> {
            Product product = productRepo.findById(orderItemRequest.getProductId())
                    .orElseThrow();

            Orderitem orderItem = new Orderitem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderItemRequest.getQuantity()))); // set
                                                                                                                 // price
                                                                                                                 // according
                                                                                                                 // to
                                                                                                                 // the
                                                                                                                 // quantity
            orderItem.setStatus(OrderStatus.PENDING);
            orderItem.setUser(user);
            return orderItem;

        }).collect(Collectors.toList());

        // calculate the total price
        BigDecimal totalPrice = orderRequest.getTotalPrice() != null
                && orderRequest.getTotalPrice().compareTo(BigDecimal.ZERO) > 0
                        ? orderRequest.getTotalPrice()
                        : orderItems.stream().map(Orderitem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        // create order entity
        Order order = new Order();
        order.setOrderItemList(orderItems);
        order.setTotalPrice(totalPrice);

        // set the order reference in each orderitem
        orderItems.forEach(orderItem -> orderItem.setOrder(order));

        orderRepo.save(order);

        return Response.builder()
                .status(200)
                .message("Order was successfully placed")
                .build();

    }

    @Override
    public Response updateOrderItemStatus(Long orderItemId, String status) {
        Orderitem orderItem = orderItemRepo.findById(orderItemId)
                .orElseThrow();

        orderItem.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        orderItemRepo.save(orderItem);
        return Response.builder()
                .status(200)
                .message("Order status updated successfully")
                .build();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Response filterOrderItems(OrderStatus status, LocalDateTime startDate, LocalDateTime endDate, Long itemId,
            Pageable pageable) {
        Specification<Orderitem> spec = Specification.where(OrderitemSpecification.hasStatus(status))
                .and((Specification) OrderitemSpecification.createdBetween(startDate, endDate))
                .and((Specification) OrderitemSpecification.hasItemId(itemId));

        Page<Orderitem> orderItemPage = orderItemRepo.findAll(spec, pageable);

        if (orderItemPage.isEmpty()) {
            throw new NotFoundException("No Order Found");
        }
        final List<OrderitemDto> orderItemDtos = orderItemPage.getContent().stream()
                .map(entityDtoMapper::mapOrderItemToDtoPlusProductAndUser)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .orderItemList(orderItemDtos)
                .totalPage(orderItemPage.getTotalPages())
                .totalElement(orderItemPage.getTotalElements())
                .build();
    }

    @SuppressWarnings("unused")
    private <R> R mapOrderItemToDtoPlusProductAndUser(Orderitem orderitem1) {
        return null;
    }

}
