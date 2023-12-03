package com.project.bookstore.bookstore.controller.api;

import com.project.bookstore.bookstore.model.dto.ItemDTO;
import com.project.bookstore.bookstore.model.entity.CartEntity;
import com.project.bookstore.bookstore.model.entity.ItemEntity;
import com.project.bookstore.bookstore.model.request.AddToCartRequest;
import com.project.bookstore.bookstore.repository.CartEntityRepository;
import com.project.bookstore.bookstore.repository.ItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartEntityRepository cartEntityRepository;

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllItemInCart(@RequestParam int userId) {
        Integer cartId = cartEntityRepository.getCartIdByUserId(userId);
        // nếu user chưa có cart -> tạo 1 cart rỗng
        // tránh trường hợp user chưa mua gì nhưng bấm xem giỏ hàng
        if(cartId == null) {
            createNewCart(userId);
        }

        List<ItemDTO> itemInCartResp = new ArrayList<>();
        List<ItemEntity> items = cartEntityRepository.getAllItemInCartByUserId(userId);
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
            itemInCartResp.add(newItem);
        }
        Map<String,Object> response = new HashMap<>();
        response.put("items", itemInCartResp);
        response.put("total_price", cartEntityRepository.getCartTotalPriceByUserId(userId));
        response.put("cart_status", cartEntityRepository.getCartStatusByUserId(userId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateItemQuantityInCart(@RequestParam int itemId, int quantity) {
        ItemEntity updatedItem = itemEntityRepository.getById(itemId);
        // số lượng thay đổi so với trước đó là bao nhiêu?
        int differenceQuantity = quantity - updatedItem.getQuantity();
        updatedItem.setQuantity(quantity);
        itemEntityRepository.save(updatedItem);

        // lấy giá của item được update số lượng
        double itemPrice = getItemPrice(updatedItem);
        // cập nhật tổng giá trị của cart
        double updatedCartPrice = updateCartTotalPrice(updatedItem.getCartId(), differenceQuantity * itemPrice);
        Map<String,Object> response = new HashMap<>();
        response.put("new_quantity", updatedItem.getQuantity());
        response.put("item_price", itemPrice);
        response.put("cart_price", updatedCartPrice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-item")
    public ResponseEntity<?> deleteItemInCart(@RequestParam int itemId) {
        ItemEntity deletedItem = itemEntityRepository.getById(itemId);
        itemEntityRepository.delete(deletedItem);
        // lấy giá của item bị xóa
        double itemPrice = getItemPrice(deletedItem);
        // cập nhật lại tổng giá tiền của cart
        double updatedCartPrice = updateCartTotalPrice(deletedItem.getCartId(), -itemPrice * deletedItem.getQuantity());
        Map<String,Object> response = new HashMap<>();
        response.put("message", "Delete Item Successfully");
        response.put("cart_price", updatedCartPrice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody AddToCartRequest request) {
        Integer cartId = cartEntityRepository.getCartIdByUserId(request.getUserId());
        Map<String, Object> response = new HashMap<>();

        // trường hợp user tồn tại giỏ hàng chưa thanh toán
        // -> thêm luôn sản phẩm vào giỏ hàng đó
        if(cartId != null) {
            return addItemToCart(request, response, cartId);
        } else {
            // chưa có giỏ hàng thì phải tạo mới
            CartEntity newCart = createNewCart(request.getUserId());
            return addItemToCart(request, response, newCart.getId());
        }
    }

    public CartEntity createNewCart(int userId) {
        CartEntity newCart = new CartEntity();
        newCart.setUserId(userId);
        newCart.setCartTotalPrice(0);
        newCart.setStatus(0);
        return cartEntityRepository.save(newCart);
    }

    public double getItemPrice(ItemEntity item) {
        if(item.getBookId() != null) {
            return itemEntityRepository.getBookItemPrice(item.getBookId());
        }
        else if(item.getMobileId() != null) {
            return itemEntityRepository.getMobileItemPrice(item.getMobileId());
        }
        else if(item.getClothesId() != null) {
            return itemEntityRepository.getClothesItemPrice(item.getClothesId());
        } else {
            return 0;
        }
    }

    public double updateCartTotalPrice(int cartId, double itemPrice) {
        CartEntity cart = cartEntityRepository.getById(cartId);
        double newPrice = cart.getCartTotalPrice() + itemPrice;
        cart.setCartTotalPrice(newPrice);
        cartEntityRepository.save(cart);
        return newPrice;
    }

    public ResponseEntity<?> addItemToCart(AddToCartRequest request, Map<String, Object> response, int cartId) {
        ItemEntity newItem;
        // kiểm tra xem người dùng đã thêm nó vào giỏ hàng hay chưa
        // nếu rồi thì thay vì thêm sản phẩm vào giỏ hàng thì update luôn số lượng
        // để sau này khi truy vấn lấy tất cả sản phẩm trong giỏ hàng không lấy ra các sản phẩm trùng lặp
        // logic này tham khảo của Shopee
        if(itemEntityRepository.existBookItemInCart(request.getBookId(), cartId) != null) {
            newItem = itemEntityRepository.existBookItemInCart(request.getBookId(), cartId);
            newItem.setQuantity(request.getQuantity() + newItem.getQuantity());
        }
        if(itemEntityRepository.existMobileItemInCart(request.getMobileId(), cartId) != null) {
            newItem = itemEntityRepository.existMobileItemInCart(request.getMobileId(), cartId);
            newItem.setQuantity(request.getQuantity() + newItem.getQuantity());
        }
        if(itemEntityRepository.existClothesItemInCart(request.getClothesId(), cartId) != null) {
            newItem = itemEntityRepository.existClothesItemInCart(request.getClothesId(), cartId);
            newItem.setQuantity(request.getQuantity() + newItem.getQuantity());
        } else {
            newItem = new ItemEntity();
            newItem.setQuantity(request.getQuantity());
        }
        ItemDTO itemDTO = new ItemDTO();
        // trường hợp sản phẩm thêm vào giỏ hàng là sách
        if(request.getBookId() != null) {
            // cấm trường hợp FE thêm cả id của mobile với clothes -> không truy vấn được ra sản phẩm
            if(request.getMobileId() != null || request.getClothesId() != null) {
                response.put("error", "Only 1 id type can be added to the request body");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            newItem.setBookId(request.getBookId());
            // cái DTO này để trả response, không ảnh hưởng đến logic
            itemDTO.setName(itemEntityRepository.getBookItemName(request.getBookId()));
            itemDTO.setPrice(itemEntityRepository.getBookItemPrice(request.getBookId()));
        }
        // trường hợp sản phẩm thêm vào giỏ hàng là điện thoại
        else if(request.getMobileId() != null) {
            if(request.getBookId() != null || request.getClothesId() != null) {
                response.put("error", "Only 1 id type can be added to the request body");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            newItem.setMobileId(request.getMobileId());
            // cái DTO này để trả response, không ảnh hưởng đến logic
            itemDTO.setName(itemEntityRepository.getMobileItemName(request.getMobileId()));
            itemDTO.setPrice(itemEntityRepository.getMobileItemPrice(request.getMobileId()));
        }
        // trường hợp sản phẩm thêm vào giỏ hàng là quần áo
        else if(request.getClothesId() != null) {
            if(request.getBookId() != null || request.getMobileId() != null) {
                response.put("error", "Only 1 id type can be added to the request body");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            newItem.setClothesId(request.getClothesId());
            itemDTO.setName(itemEntityRepository.getClothesItemName(request.getClothesId()));
            itemDTO.setPrice(itemEntityRepository.getClothesItemPrice(request.getClothesId()));
        } else {
            // trường hợp FE không thêm id ở cả 3 trường -> báo lỗi
            response.put("error", "Book id, Mobile id, Clothes id cannot be null at the same time");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        newItem.setCartId(cartId);
        ItemEntity savedItem = itemEntityRepository.save(newItem);
        itemDTO.setQuantity(request.getQuantity());
        itemDTO.setId(savedItem.getId());
        // thêm sản phẩm vào giỏ hàng xong phải update giá tiền tổng của giỏ hàng luôn
        updateCartTotalPrice(cartId, itemDTO.getPrice() * itemDTO.getQuantity());
        return ResponseEntity.ok(itemDTO);
    }
}
