package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.dto.ItemDTO;
import com.project.bookstore.bookstore.model.entity.CartEntity;
import com.project.bookstore.bookstore.model.entity.ItemEntity;
import com.project.bookstore.bookstore.model.entity.OrdersEntity;
import com.project.bookstore.bookstore.model.entity.VoucherEntity;
import com.project.bookstore.bookstore.model.request.UpdateOrderRequest;
import com.project.bookstore.bookstore.repository.CartEntityRepository;
import com.project.bookstore.bookstore.repository.ItemEntityRepository;
import com.project.bookstore.bookstore.repository.OrdersEntityRepository;
import com.project.bookstore.bookstore.repository.VoucherEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrdersEntityRepository ordersEntityRepository;

    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @Autowired
    private VoucherEntityRepository voucherEntityRepository;

    @GetMapping("/by-cart-id")
    public ResponseEntity<?> getOrderByCartId(@RequestParam int cartId) {
        OrdersEntity ordersEntity = ordersEntityRepository.getOrdersEntitiesByCartId(cartId);
        CartEntity cart = cartEntityRepository.getCartEntityById(cartId);
        // chưa có order thì tạo mới rồi mới get
        if(ordersEntity == null) {
            ordersEntity = new OrdersEntity();
            ordersEntity.setCustomerIdCus(cart.getUserId());
            ordersEntity.setOrderTotalPrice(cart.getCartTotalPrice() + 10);
            ordersEntity.setStatus(0);
            ordersEntity.setCartId(cartId);
            ordersEntity.setPaymentType(0);
            ordersEntity.setShipmentType(0);
            ordersEntityRepository.save(ordersEntity);
        }
        return orderResponse(ordersEntity, cart);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOrder(@RequestParam int userId) {
        // lấy tất cả order mà user đã từng đặt
        return new ResponseEntity<>(ordersEntityRepository.getOrdersEntitiesByCustomerIdCus(userId), HttpStatus.OK);
    }

    @GetMapping("/by-id")
    public ResponseEntity<?> getOrderById(@RequestParam int id) {
        // sử dụng hàm này khi user muốn xem lại chi tiết order của mình (ở trang danh sách order)
        OrdersEntity ordersEntity = ordersEntityRepository.getOrdersEntityById(id);
        CartEntity cart = cartEntityRepository.getCartEntityById(ordersEntity.getCartId());
        return orderResponse(ordersEntity, cart);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderRequest request) {
        OrdersEntity ordersEntity = ordersEntityRepository.getOrdersEntityById(request.getOrderId());
        CartEntity cartEntity = cartEntityRepository.getCartEntityById(ordersEntity.getCartId());
        if(request.getVoucherId() != null) {
            // sử dụng trong trường hợp bỏ chọn order
            if(request.getVoucherId() == 0) {
                ordersEntity.setVoucherId(null);
            }
            else {
                ordersEntity.setVoucherId(request.getVoucherId());
            }
            ordersEntity.setOrderTotalPrice(calculateTotalPrice(ordersEntity, cartEntity));
        }
        if(request.getPaymentType() != null) {
            ordersEntity.setPaymentType(request.getPaymentType());
        }
        if(request.getShipmentType() != null) {
            ordersEntity.setShipmentType(request.getShipmentType());
        }
        ordersEntityRepository.save(ordersEntity);
        CartEntity cart = cartEntityRepository.getCartEntityById(ordersEntity.getCartId());
        return orderResponse(ordersEntity, cart);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> createOrder(@RequestParam int orderId) {
        // trigger khi bấm submit order
        // status = 1 (đã thanh toán)
        OrdersEntity ordersEntity = ordersEntityRepository.getOrdersEntityById(orderId);
        ordersEntity.setStatus(1);
        ordersEntityRepository.save(ordersEntity);
        CartEntity cart = cartEntityRepository.getCartEntityById(ordersEntity.getCartId());
        // status = 1 (đã thanh toán) -> sẽ không còn hiển thị các item ở trong cart nữa
        cart.setStatus(1);
        cartEntityRepository.save(cart);
        return orderResponse(ordersEntity, cart);
    }

    public ResponseEntity<?> orderResponse(OrdersEntity ordersEntity, CartEntity cart) {
        Map<String,Object> response = new HashMap<>();
        response.put("items", getItems(cart.getId()));
        response.put("status", ordersEntity.getStatus());
        response.put("voucher", ordersEntity.getVoucherId());
        response.put("payment_type", ordersEntity.getPaymentType());
        response.put("shipment_type", ordersEntity.getShipmentType());
        response.put("shipment_discount", calculateShipmentDiscount(ordersEntity));
        response.put("payment_discount", calculatePaymentDiscount(ordersEntity, cart));
        response.put("items_price", cart.getCartTotalPrice());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public double calculateShipmentDiscount(OrdersEntity ordersEntity) {
        VoucherEntity voucher = voucherEntityRepository.getVoucherEntityById(ordersEntity.getVoucherId());
        if(voucher != null && voucher.getVoucherType() == 0) {
            double shipFees = 0;
            if(ordersEntity.getShipmentType() == 0) {
                shipFees = 10;
            } else if(ordersEntity.getShipmentType() == 1) {
                shipFees = 20;
            }
            return shipFees * voucher.getDiscountPercent() / 100;
        } else {
            return 0;
        }
    }

    public double calculatePaymentDiscount(OrdersEntity ordersEntity, CartEntity cart) {
        VoucherEntity voucher = voucherEntityRepository.getVoucherEntityById(ordersEntity.getVoucherId());
        if(voucher != null && voucher.getVoucherType() == 1) {
            return cart.getCartTotalPrice() * voucher.getDiscountPercent() / 100;
        } else {
            return 0;
        }
    }

    public double calculateTotalPrice(OrdersEntity order, CartEntity cart) {
        double shipFees = 0;
        if(order.getShipmentType() == 0) {
            shipFees = 10;
        } else if(order.getShipmentType() == 1) {
            shipFees = 20;
        }
        return cart.getCartTotalPrice() + shipFees - calculatePaymentDiscount(order, cart) - calculateShipmentDiscount(order);
    }

    public List<ItemDTO> getItems(int cartId) {
        List<ItemDTO> itemInCart = new ArrayList<>();
        List<ItemEntity> items = itemEntityRepository.getItemEntitiesByCartId(cartId);
        for(int i = 0; i < items.size(); i++) {
            ItemDTO newItem = new ItemDTO();
            newItem.setId(items.get(i).getId());
            newItem.setQuantity(items.get(i).getQuantity());
            if(items.get(i).getBookId() != null) {
                newItem.setName(itemEntityRepository.getBookItemName(items.get(i).getBookId()));
                newItem.setPrice(itemEntityRepository.getBookItemPrice(items.get(i).getBookId()));
            }
            else if(items.get(i).getMobileId() != null) {
                newItem.setName(itemEntityRepository.getMobileItemName(items.get(i).getMobileId()));
                newItem.setPrice(itemEntityRepository.getMobileItemPrice(items.get(i).getMobileId()));
            }
            else if(items.get(i).getClothesId() != null) {
                newItem.setName(itemEntityRepository.getClothesItemName(items.get(i).getClothesId()));
                newItem.setPrice(itemEntityRepository.getClothesItemPrice(items.get(i).getClothesId()));
            }
            itemInCart.add(newItem);
        }
        return itemInCart;
    }
}
