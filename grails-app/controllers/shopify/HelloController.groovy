package shopify

class HelloController {

    def index() {
        ShopifyService.getOrders()
        render "Hello World!"
    }

    def fetch() {
        ShopifyService.getOrders()
    }

    def delete() {
        ShopifyService.deleteOrders();
    }
}
