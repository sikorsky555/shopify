package shopify

class Product {
    long id
    String title
    static constraints = {
        id generator: 'assigned'
    }
}
