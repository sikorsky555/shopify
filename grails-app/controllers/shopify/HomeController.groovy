package shopify

class HomeController {
    def index() {
        respond([orderList: ProductOrder.list()])
    }
}
