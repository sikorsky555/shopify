package shopify

class Customer {
    long id
    String first_name
    String last_name
    static constraints = {
        id generator: 'assigned'
    }
}
