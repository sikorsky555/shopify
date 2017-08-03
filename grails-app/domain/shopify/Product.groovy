package shopify

class Product {
    int id
    String title
    static constraints = {
        id generator: 'assigned'
    }
}
