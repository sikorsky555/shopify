package shopify

class OrdersController {

    def apiService

    // TODO: These should be post methods since they are modifying records
    def index() {
        apiService.getAllData()
        redirect(controller: 'home')
    }

    def delete() {
        def productOrders = ProductOrder.list()
        for(def productOrder: productOrders) {
            productOrder.delete(flush: true)
        }

        def lineItems = LineItem.list()
        for(def lineItem: lineItems) {
            lineItem.delete(flush: true)
        }

        def customers = Customer.list()
        for(def cust: customers) {
            cust.delete(flush: true)
        }

        def products = Product.list()
        for(def product: products) {
            product.delete(flush: true)
        }

        redirect(controller: 'home')
    }
}
