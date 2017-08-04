package shopify

class LineItem {
    long id
    String name
    String price
    String vendor

    static belongsTo = [product: Product]

    static constraints = {
        id generator: 'assigned'
    }
}
