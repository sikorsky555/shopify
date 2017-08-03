package shopify

class ProductOrder {
    int id
    String email
    Customer customer
    List lineItems
    String total_price

    static hasMany = [lineItems: LineItem]

    static constraints = {
        id generator: 'assigned'
    }
}
