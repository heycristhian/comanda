package br.com.heycristhian.comanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ComandaApplication {

    public static void main(String[] args) {

//        var product = Product.builder()
//                .name("Heinekein")
//                .price(BigDecimal.valueOf(10.00))
//                .build();
//
//        var product2 = Product.builder()
//                .name("Porção de batata")
//                .price(BigDecimal.valueOf(50.00))
//                .build();
//
//        var cristhianClient = Client.builder()
//                .firstName("Cristhian")
//                .lastName("Dias")
//                .phone("18997415398")
//                .build();
//
//        var eloisaClient = Client.builder()
//                .firstName("Eloisa")
//                .lastName("Rozendo")
//                .phone("18997415398")
//                .build();
//
//        var veraClient = Client.builder()
//                .firstName("Vera")
//                .lastName("Lucia")
//                .phone("18997415398")
//                .build();
//
//        var clients = List.of(
//                cristhianClient,
//                eloisaClient,
//                veraClient
//        );
//
//        var table = Table.builder()
//                .number(1)
//                .orders(null)
//                .clients(clients)
//                .build();
//
//        var quantityHeinekein = 2;
//
//        var clientsOrder1 = List.of(eloisaClient, cristhianClient);
//        var totalOrder1 = product.getPrice().multiply(BigDecimal.valueOf(quantityHeinekein));
//
//        var order1 = Order.builder()
//                .product(product)
//                .quantity(quantityHeinekein)
//                .total(totalOrder1)
//                .totalEach(totalOrder1.divide(BigDecimal.valueOf(clientsOrder1.size()), 2, RoundingMode.DOWN))
//                .clients(clientsOrder1)
//                .build();
//
//        table.addOrder(order1);
//
//        var quantityPorcao = 1;
//        var clientsOrder2 = List.of(eloisaClient, cristhianClient);
//        var totalOrder2 = product2.getPrice().multiply(BigDecimal.valueOf(quantityPorcao));
//
//        var order2 = Order.builder()
//                .product(product2)
//                .quantity(quantityPorcao)
//                .total(product2.getPrice().multiply(BigDecimal.valueOf(quantityPorcao)))
//                .totalEach(totalOrder2.divide(BigDecimal.valueOf(clientsOrder2.size()), 2, RoundingMode.DOWN))
//                .clients(List.of(eloisaClient, cristhianClient))
//                .build();
//
//        table.addOrder(order2);
//
//        var map = new HashMap<String, List<CloseClientDetailResponse>>();
//
//
//        table.getOrders()
//                .forEach(order -> order.getClients()
//                        .forEach(client -> {
//                            List<CloseClientDetailResponse> detailsImmutable = map.get(client.getFirstName());
//
//                            if (isNull(detailsImmutable)) {
//                                map.put(client.getFirstName(), List.of(
//                                        CloseClientDetailResponse.builder()
//                                                .portion("1/" + order.getClients().size())
//                                                .productName(order.getProduct().getName())
//                                                .quantity(order.getQuantity())
//                                                .total(order.getTotalEach())
//                                                .instant(Instant.now())
//                                                .build()
//                                ));
//                            } else {
//                                List<CloseClientDetailResponse> detailsMutable = new ArrayList<>(detailsImmutable);
//                                detailsMutable.add(
//                                        CloseClientDetailResponse.builder()
//                                                .portion("1/" + order.getClients().size())
//                                                .productName(order.getProduct().getName())
//                                                .quantity(order.getQuantity())
//                                                .total(order.getTotalEach())
//                                                .instant(Instant.now())
//                                                .build()
//                                );
//                                map.put(client.getFirstName(), detailsMutable);
//                            }
//                        })
//                );
//
//        List<CloseClientResponse> clientsCloseResponse = new ArrayList<>();
//
//        map.forEach((firstName, clientCloseDetails) -> clientsCloseResponse.add(
//                CloseClientResponse.builder()
//                        .name(firstName)
//                        .closeClientDetailsResponse(clientCloseDetails)
//                        .total(
//                                clientCloseDetails.
//                                        stream()
//                                        .map(CloseClientDetailResponse::getTotal)
//                                        .reduce(BigDecimal.ZERO, BigDecimal::add)
//                        )
//                        .build()
//        ));
//
//        var closeTable = CloseTableResponse.builder()
//                .tableId(UUID.randomUUID())
//                .closeClientsResponse(clientsCloseResponse)
//                .build();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//
//        try {
//            String closeTableJson = objectMapper.writeValueAsString(closeTable);
//            System.out.println("closeTableJson1: " + closeTableJson);

//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

        SpringApplication.run(ComandaApplication.class, args);
    }
}
