package com.bytecoder.java8.FunctionalProgramming;
//package com.gschoudhary.java8.FunctionalProgramming;
//
//import lombok.*;
//
//import java.util.Optional;
//import java.util.function.Function;
//
//class OrderDto {
//}
//class OrderEntity {
//}
//class Address {
//}
//class OrderDtoConverter implements Function<Optional<OrderDto>, Optional<OrderEntity>> {
//    @Override
//    public Optional<OrderEntity> apply(Optional<OrderDto> orderDto) {
//        if (orderDto.isPresent()) {
//            return Optional.ofNullable(new OrderEntity());
//        }
//        return Optional.empty();
//    }
//}
//class OrderEntityConverter implements Function<Optional<OrderEntity>, Optional<OrderDto>> {
//    @Override
//    public Optional<OrderDto> apply(Optional<OrderEntity> orderEntity) {
//        if (orderEntity.isPresent()) {
//            return Optional.ofNullable(new OrderDto());
//        }
//        return Optional.empty();
//    }
//}
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class CustomerEntity {
//    public static void main(String[] args) {
//
////        orderDto -> get address, get_customer-> update business -> create order entity -> save in repo ->
////        get entity-> convert dto ->
//        Function<Integer, CustomerEntity> customerFetchFunction = (id) -> new CustomerEntity();
//        Function<Integer, Address> addressFunction = (id) -> new Address();
//
//        Function<Optional<OrderDto>, OrderDto> PushOrderDtoMessageFunction = orderDto ->{
//            System.out.println("pushing kafka messages");
//            return   orderDto.get();
//        } ;
//        Function<OrderDto, Optional<OrderDto>> bussinessLogicFunction = orderDto1 -> {
//            System.out.println("fetch & update address and customer");
//            customerFetchFunction.apply(1);
//            addressFunction.apply(1);
//            return Optional.ofNullable(orderDto1);
//        };
//
//        OrderDto orderDto = Optional.ofNullable(new OrderDto())
//                .map(bussinessLogicFunction)
//                .map(new OrderDtoConverter())
//                .map(orderEntity -> {
//                    System.out.println("save in repo");
//                    return orderEntity;
//                })
//                .map(new OrderEntityConverter())
//                .map(PushOrderDtoMessageFunction)
//                .orElseThrow(() -> new RuntimeException("We are not able to process your order, please try again."));
//
//
//
//    }
//
//
//}
