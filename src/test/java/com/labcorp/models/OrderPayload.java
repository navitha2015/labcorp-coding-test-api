package com.labcorp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class OrderPayload {

    @JsonProperty("order_id")
    private String orderId;

    private Customer customer;
    private List<Item> items;
    private Payment payment;
    private Shipping shipping;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("created_at")
    private String createdAt;

    public OrderPayload() {
        // Default constructor for valid order
        this.orderId = "12345";
        this.customer = new Customer();
        this.items = Arrays.asList(
                new Item("A101", "Wireless Headphones", 1, 79.99),
                new Item("B202", "Smartphone Case", 2, 15.99)
        );
        this.payment = new Payment();
        this.shipping = new Shipping();
        this.orderStatus = "processing";
        this.createdAt = "2024-11-07T12:00:00Z";
    }

    public String getValidOrderJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert order to JSON", e);
        }
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public Shipping getShipping() { return shipping; }
    public void setShipping(Shipping shipping) { this.shipping = shipping; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    // Inner Classes
    public static class Customer {
        private String name;
        private String email;
        private String phone;
        private Address address;

        public Customer() {
            this.name = "Jane Smith";
            this.email = "janesmith@example.com";
            this.phone = "1-987-654-3210";
            this.address = new Address();
        }

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public Address getAddress() { return address; }
        public void setAddress(Address address) { this.address = address; }
    }

    public static class Address {
        private String street;
        private String city;
        private String state;
        private String zipcode;
        private String country;

        public Address() {
            this.street = "456 Oak Street";
            this.city = "Metropolis";
            this.state = "NY";
            this.zipcode = "10001";
            this.country = "USA";
        }

        // Getters and Setters
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }

        public String getState() { return state; }
        public void setState(String state) { this.state = state; }

        public String getZipcode() { return zipcode; }
        public void setZipcode(String zipcode) { this.zipcode = zipcode; }

        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }

    public static class Item {
        @JsonProperty("product_id")
        private String productId;
        private String name;
        private int quantity;
        private double price;

        public Item() {}

        public Item(String productId, String name, int quantity, double price) {
            this.productId = productId;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        // Getters and Setters
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }

    public static class Payment {
        private String method;

        @JsonProperty("transaction_id")
        private String transactionId;
        private double amount;
        private String currency;

        public Payment() {
            this.method = "credit_card";
            this.transactionId = "txn_67890";
            this.amount = 111.97;
            this.currency = "USD";
        }

        // Getters and Setters
        public String getMethod() { return method; }
        public void setMethod(String method) { this.method = method; }

        public String getTransactionId() { return transactionId; }
        public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
    }

    public static class Shipping {
        private String method;
        private double cost;

        @JsonProperty("estimated_delivery")
        private String estimatedDelivery;

        public Shipping() {
            this.method = "standard";
            this.cost = 5.99;
            this.estimatedDelivery = "2024-11-15";
        }

        // Getters and Setters
        public String getMethod() { return method; }
        public void setMethod(String method) { this.method = method; }

        public double getCost() { return cost; }
        public void setCost(double cost) { this.cost = cost; }

        public String getEstimatedDelivery() { return estimatedDelivery; }
        public void setEstimatedDelivery(String estimatedDelivery) { this.estimatedDelivery = estimatedDelivery; }
    }
}