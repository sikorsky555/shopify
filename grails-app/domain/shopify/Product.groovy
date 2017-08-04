package shopify

class Product {
    long id
    String title
    String image_url
    static constraints = {
        id generator: 'assigned'
        image_url nullable: true
    }
}
