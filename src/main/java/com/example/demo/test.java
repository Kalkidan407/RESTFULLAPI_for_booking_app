// @ProvidedService
// @RequiredArgsConstructor
// public class OrderService {
//     private final OrderRepository orderRepository;
//     private final UserRepository userRepository;
//     private final ServiceRepository serviceRepository;

//     public Order createOrder(OrderRequest orderRequest) {
//         User user = userRepository.findById(orderRequest.getUserId())
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         ProvidedService service = serviceRepository.findById(orderRequest.getServiceId())
//                 .orElseThrow(() -> new RuntimeException("Service not found"));

//         Order order = new Order();
//         order.setUser(user);
//         order.setService(service);
//         order.setOrderDate(orderRequest.getOrderDate());
//         order.setStatus(orderRequest.getStatus());
//         order.setDeliveryDate(orderRequest.getDeliveryDate());

//         return orderRepository.save(order);
//     }
// }@
