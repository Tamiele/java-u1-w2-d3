import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Product prodotto1 = new Product(1253464564, "Il Sognore degli Anelli", "Books", 50.55);
        Product prodotto2 = new Product(1253464564, "Programmare Insieme", "Books", 35);
        Product prodotto3 = new Product(1253464564, "Le Avventure di Michele", "Books", 115.25);
        Product prodotto4 = new Product(1253464564, "Ciuccio", "Baby", 5.50);
        Product prodotto5 = new Product(1253464564, "Tutina", "Baby", 15);
        Product prodotto6 = new Product(1253464564, "Felpa", "Boys", 50);


        List<Product> arrayProdotti = new ArrayList<>();

        arrayProdotti.add(prodotto1);
        arrayProdotti.add(prodotto2);
        arrayProdotti.add(prodotto3);
        arrayProdotti.add(prodotto4);
        arrayProdotti.add(prodotto5);
        arrayProdotti.add(prodotto6);

        List<Product> booksProduct = arrayProdotti.stream()
                .filter(books -> books.getCategory().equals("Books") && books.getPrice() > 100)
                .toList();


        booksProduct.forEach(System.out::println);

        List<Product> babyProduct = arrayProdotti.stream()
                .filter(baby -> baby.getCategory().equals("Baby"))
                .toList();

        babyProduct.forEach(System.out::println);


        List<Product> boysProduct = arrayProdotti.stream()
                .filter(boys -> boys.getCategory().equals("Boys"))
                .peek(boys -> boys.setPrice(boys.getPrice() * 0.9))
                .toList();

        boysProduct.forEach(System.out::println);


        //////////////////////////////Esercizio 4////////////////////////////////////////

        // Creazione dei clienti
        Customer c1 = new Customer(1, "Gianni", 1);
        Customer c2 = new Customer(2, "Marco", 2);
        Customer c3 = new Customer(3, "Fabio", 3);

        // Creazione degli ordini
        Order ordine1 = new Order(c2); // Cliente tier 2
        ordine1.setOrderDate(LocalDate.of(2021, Month.FEBRUARY, 15));
        ordine1.setProducts(List.of(prodotto1, prodotto2)); // Prodotti inclusi

        Order ordine2 = new Order(c1); // Cliente tier 1
        ordine2.setOrderDate(LocalDate.of(2021, Month.MARCH, 10)); // Dentro il range
        ordine2.setProducts(List.of(prodotto3));

        Order ordine3 = new Order(c3); // Cliente tier 3
        ordine3.setOrderDate(LocalDate.of(2021, Month.JANUARY, 25)); // Fuori dal range
        ordine3.setProducts(List.of(prodotto4, prodotto5));

        // Lista di ordini
        List<Order> ordini = new ArrayList<>();
        ordini.add(ordine1);
        ordini.add(ordine2);
        ordini.add(ordine3);

        // Filtro degli ordini richiesti (tier 2, data tra 01-Feb-2021 e 01-Apr-2021)
        LocalDate startDate = LocalDate.of(2021, Month.FEBRUARY, 1);
        LocalDate endDate = LocalDate.of(2021, Month.APRIL, 1);

        List<Order> ordiniFiltrati = ordini.stream()
                .filter(o -> o.getCustomer().getTier() == 2) // Filtra clienti di tier 2
                .filter(o -> o.getOrderDate().isAfter(startDate.minusDays(1)) &&
                        o.getOrderDate().isBefore(endDate.plusDays(1))) // Filtra date tra 01-Feb e 01-Apr
                .toList();

        // Stampa gli ordini filtrati
        ordiniFiltrati.forEach(System.out::println);
    }


}
