package shopify

class Customer {

    String name
    static constraints = {
        id generator: 'assigned'
    }
}
