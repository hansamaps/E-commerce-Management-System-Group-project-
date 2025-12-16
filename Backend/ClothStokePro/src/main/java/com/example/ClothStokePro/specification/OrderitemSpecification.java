package com.example.ClothStokePro.specification;

import com.example.ClothStokePro.entity.Order;
import com.example.ClothStokePro.entity.Orderitem;
import com.example.ClothStokePro.enums.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class OrderitemSpecification {

    /** Specification to filter order items by status */
    public static Specification<Orderitem> hasStatus(OrderStatus status) {
        return ((root, query, criteriaBuilder) -> status != null ? criteriaBuilder.equal(root.get("status"), status)
                : null);

    }

    /** Specification to filter order items by data range */
    public static Specification<Orderitem> createdBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return ((root, query, criteriaBuilder) -> {
            if (startDate != null && endDate != null) {
                return criteriaBuilder.between(root.get("createdAt"), startDate, endDate);
            } else if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
            } else if (endDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate);
            } else {
                return null;
            }
        });
    }

    /** Generate specification to filter orderitems by item id */
    public static Specification<Orderitem> hasItemId(Long itemId) {
        return ((root, query, criteriaBuilder) -> itemId != null ? criteriaBuilder.equal(root.get("id"), itemId)
                : null);
    }
}
