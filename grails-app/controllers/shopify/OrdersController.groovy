package shopify

class OrdersController {

    def apiService

    def index() {
        apiService.getOrders()
        redirect(controller: 'hello', action:'index')
    }
}