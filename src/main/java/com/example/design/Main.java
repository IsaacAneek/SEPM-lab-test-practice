package com.example.design;

class RestaurantConfig {

    private static RestaurantConfig instance;

    public String restaurantName = "Food Corner";
    public String currency = "BDT";
    public double discount = 10;

    private RestaurantConfig() {}

    public static RestaurantConfig getInstance() {

        if (instance == null) {
            instance = new RestaurantConfig();
        }

        return instance;
    }
}

interface Payment {
    void pay(double amount);
}

class CashPaymnet implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " in cash.");
    }
}

class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using card.");
    }
}

class BKashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using bKash.");
    }
}

class PaymentFactory {
    public static Payment getPaymentMethod(String type) {
        Payment payment;
        if (type.equalsIgnoreCase("cash")) {
            payment = new CashPaymnet();
        } else if (type.equalsIgnoreCase("card")) {
            payment = new CardPayment();
        } else if (type.equalsIgnoreCase("bkash")) {
            payment = new BKashPayment();
        } else if (type.equalsIgnoreCase("nagad")) {
            payment = new NagadPaymentAdapter();
        } else {
        throw new IllegalArgumentException("Invalid payment type");
    }

    return new PaymentProxy(payment);
}
}

class NagadPayment {
    public void Nagadpay(double amount) {
        System.out.println("Paid " + amount + " using Nagad Interfance.");
    }
}

class NagadPaymentAdapter implements Payment {
    private NagadPayment nagadPayment = new NagadPayment();

    @Override
    public void pay(double amount) {
        nagadPayment.Nagadpay(amount);
    }
}

interface OrderFormatter {
    void formatOrder(Order order);
}

class OrderFormatterPDF implements OrderFormatter {
    @Override
    public void formatOrder(Order order) {
        System.out.println("Generated PDF");
        System.out.println("PDF Order: Amount = " + order.getAmount());
    }
}

class OrderFormatterHTML implements OrderFormatter {
    @Override
    public void formatOrder(Order order) {
        System.out.println("Generated HTML");
        System.out.println("HTML Order: Amount = " + order.getAmount());
    }
}

class Order {
    private double amount;
    private Payment paymentMethod;
    private OrderFormatter orderFormatter;

    public Order(double amount, Payment paymentMethod, OrderFormatter orderFormatter) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.orderFormatter = orderFormatter;
    }

    public void processOrder() {
        double discountAmount = amount * (RestaurantConfig.getInstance().discount / 100);
        double finalAmount = amount - discountAmount;
        paymentMethod.pay(finalAmount);
        orderFormatter.formatOrder(this);
    }

    public double getAmount() {
        return amount;
    }
}

class PaymentProxy implements Payment {
    private Payment payment;

    public PaymentProxy(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Payment is being processed...");
        if(amount <= 0) {
            System.out.println("Invalid amount. Payment failed.");
            return;
        }
        System.out.println("Validating payment...");
        payment.pay(amount);
        System.out.println("Payment completed.");
    }
}


public class Main {
    public static void main(String[] args) {

        // 1. Singleton Pattern
        RestaurantConfig config = RestaurantConfig.getInstance();

        // 2. Hardcoded order information
        String customer = "Rahim";
        String items = "Burger, Pizza, Coffee";

        double subtotal = 700.0;
        double total = subtotal;

        // 3. Factory + Adapter + Proxy Patterns
        Payment payment = PaymentFactory.getPaymentMethod("bkash");

        payment.pay(total);

        // 4. Bridge Pattern
        Order customerOrder = new Order(total, payment, new OrderFormatterPDF());

        customerOrder.processOrder();
    }
}