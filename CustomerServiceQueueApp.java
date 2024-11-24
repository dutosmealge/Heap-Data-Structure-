import java.util.Scanner;

//Class representing a Max Heap 
class MaxHeap {
    private Customer[] heap;
    private int size;
    private int capacity;

    // Constructor 
    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new Customer[capacity];
    }

    // Method to insert 
    public void insert(Customer customer) {
        if (size >= capacity) {
            System.out.println("Queue is full. Cannot insert.");
            return;
        }
        heap[size] = customer;
        size++;
        heapifyUp();
    }

    // Method to extract 
    public Customer extractMax() {
        if (size == 0) {
            System.out.println("Queue is empty. Cannot extract.");
            return null;
        }
        Customer max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return max;
    }

    // Heapify the heap 
    private void heapifyUp() {
        int index = size - 1;
        while (index > 0 && heap[parent(index)].getPriority() < heap[index].getPriority()) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (leftChild(index) < size) {
            int largerChild = leftChild(index);
            if (rightChild(index) < size && heap[rightChild(index)].getPriority() > heap[leftChild(index)].getPriority()) {
                largerChild = rightChild(index);
            }
            if (heap[index].getPriority() >= heap[largerChild].getPriority()) {
                break;
            }
            swap(index, largerChild);
            index = largerChild;
        }
    }

    // Helper methods 
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    
    private void swap(int index1, int index2) {
        Customer temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    // Method to display 
    public void displayHeap() {
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }
    }
}

//Class to represent a customer request
class Customer {
    private String name;
    private int priority;  

    public Customer(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Customer: " + name + ", Priority: " + priority;
    }
}

public class CustomerServiceQueueApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the maximum number of customers in the queue:");
        int capacity = scanner.nextInt();
        scanner.nextLine();  
        MaxHeap queue = new MaxHeap(capacity);

        while (true) {
            System.out.println("\nCustomer Service Queue Menu:");
            System.out.println("1. Add customer request");
            System.out.println("2. Process highest priority request");
            System.out.println("3. Display queue");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("Enter customer name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter priority (higher number = higher priority):");
                    int priority = scanner.nextInt();
                    Customer customer = new Customer(name, priority);
                    queue.insert(customer);
                    break;

                case 2:
                    Customer maxCustomer = queue.extractMax();
                    if (maxCustomer != null) {
                        System.out.println("Processing request: " + maxCustomer);
                    }
                    break;

                case 3:
                    System.out.println("Current customer service queue:");
                    queue.displayHeap();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}