package shopify

class ProductOrder {
    long id
    String email
    String total_price

    static belongsTo = [customer: Customer]
    static hasMany = [line_items: LineItem]

    static constraints = {
        id generator: 'assigned'
        email nullable: true
        customer nullable: true
    }

}
