package com.demo.bankapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CustomerCRUD {

    private static SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // Reads hibernate.cfg.xml by default
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Customer.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerCRUD crudOperations = new CustomerCRUD();

        while (true) {
            System.out.println("\nBanking Operations:");
            System.out.println("1. Insert New Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. View Customer by ID");
            System.out.println("4. Update Customer Details");
            System.out.println("5. Delete Customer by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    crudOperations.insertCustomer(scanner);
                    break;
                case 2:
                    crudOperations.viewAllCustomers();
                    break;
                case 3:
                    crudOperations.viewCustomerById(scanner);
                    break;
                case 4:
                    crudOperations.updateCustomer(scanner);
                    break;
                case 5:
                    crudOperations.deleteCustomer(scanner);
                    break;
                case 6:
                    System.out.println("Exiting application.");
                    if (sessionFactory != null) {
                        sessionFactory.close();
                    }
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void insertCustomer(Scanner scanner) {
        System.out.println("\nEnter Customer Details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email ID: ");
        String emailID = scanner.nextLine();
        System.out.print("Mobile No: ");
        String mobileNo = scanner.nextLine();
        System.out.print("Account Type (S/C): ");
        char acctType = scanner.nextLine().toUpperCase().charAt(0);
        System.out.print("Date of Opening (YYYY-MM-DD): ");
        LocalDate dateOfOpening = LocalDate.parse(scanner.nextLine());

        Customer customer = new Customer(name, emailID, mobileNo, acctType, dateOfOpening);

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(customer);
            transaction.commit();
            System.out.println("Customer record inserted successfully with ID: " + customer.getCustomerId());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void viewAllCustomers() {
        try (Session session = sessionFactory.openSession()) {
            List<Customer> customers = session.createQuery("from Customer", Customer.class).list();
            if (customers.isEmpty()) {
                System.out.println("No customer records found.");
            } else {
                System.out.println("\n--- All Customers ---");
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
                System.out.println("----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewCustomerById(Scanner scanner) {
        System.out.print("\nEnter Customer ID to view: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                System.out.println("\n--- Customer Details ---");
                System.out.println(customer);
                System.out.println("------------------------");
            } else {
                System.out.println("Customer with ID " + customerId + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Scanner scanner) {
        System.out.print("\nEnter Customer ID to update: ");
        int customerIdToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, customerIdToUpdate);
            if (customer != null) {
                System.out.println("\n--- Current Customer Details ---");
                System.out.println(customer);
                System.out.println("\n--- Enter New Details (Leave blank to keep current) ---");

                System.out.print("New Email ID: ");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty()) {
                    customer.setEmailID(newEmail);
                }

                System.out.print("New Mobile No: ");
                String newMobile = scanner.nextLine();
                if (!newMobile.isEmpty()) {
                    customer.setMobileNo(newMobile);
                }

                transaction = session.beginTransaction();
                session.merge(customer);
                transaction.commit();
                System.out.println("Customer with ID " + customerIdToUpdate + " updated successfully.");
            } else {
                System.out.println("Customer with ID " + customerIdToUpdate + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCustomer(Scanner scanner) {
        System.out.print("\nEnter Customer ID to delete: ");
        int customerIdToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, customerIdToDelete);
            if (customer != null) {
                System.out.println("\n--- Customer Details to be Deleted ---");
                System.out.println(customer);
                System.out.print("Are you sure you want to delete this customer? (yes/no): ");
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.equals("yes")) {
                    Transaction transaction = null;
                    try {
                        transaction = session.beginTransaction();
                        session.remove(customer);
                        transaction.commit();
                        System.out.println("Customer with ID " + customerIdToDelete + " deleted successfully.");
                    } catch (Exception e) {
                        if (transaction != null) {
                            transaction.rollback();
                        }
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Deletion cancelled.");
                }
            } else {
                System.out.println("Customer with ID " + customerIdToDelete + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

