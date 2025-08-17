
// // User.java
// @Entity
// @Table(name = "users")
// public class User {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String email;
//     private String phone;
//     private String role; // CUSTOMER, DESIGNER, ADMIN
// }
// Service.java



// Service.java
// @Entity
// @Table(name = "services")
// public class Service {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;
//     private String description;
//     private Double price;
// }

// Order.java
// @Entity
// @Table(name = "orders")
// public class Order {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "customer_id")
//     private User customer;

//     @ManyToOne
//     @JoinColumn(name = "service_id")
//     private Service service;

//     private String status; // PENDING, IN_PROGRESS, COMPLETED
//     private LocalDate orderDate;
//     private LocalDate deliveryDate;
// }
