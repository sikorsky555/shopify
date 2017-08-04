package shopify

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class ApiService {

    def getAllData() {
        getProducts()
        getCustomers()
        getOrders()
    }

    def getOrders() {
        def parsedData = getShopifyData("orders")
        for (def order: parsedData.orders) {
            def productOrder = new ProductOrder(
                    id: order.id,
                    email: order.email,
                    total_price: order.total_price
            )
            productOrder.id = order.id
            for (def dbLineItem: order.line_items) {
                def lineItem = new LineItem(
                        id: dbLineItem.id,
                        name: dbLineItem.name,
                        price: dbLineItem.price,
                        vendor: dbLineItem.vendor,
                        quantity: dbLineItem.quantity
                )
                lineItem.id = dbLineItem.id
                lineItem.product = Product.findById(dbLineItem.product_id)
                if (!LineItem.exists(dbLineItem.id)) {
                    lineItem.save(failOnError: true, flush: true)
                }
                productOrder.addToLine_items(lineItem)
            }
            if (order.customer) {
                def customer = new Customer(
                        id: order.customer.id,
                        first_name: order.customer.first_name,
                        last_name: order.customer.last_name,
                        email: order.customer.email
                )
                customer.id = order.customer.id
                if (!Customer.exists(customer.id)) {
                    customer.save(failOnError: true, flush: true)
                }
                productOrder.customer = customer
            }

            if (!ProductOrder.exists(productOrder.id)) {
                productOrder.save(failOnError: true, flush: true)
            }
        }
    }

    def getCustomers() {
        def parsedData = getShopifyData("customers")
        for (def cust: parsedData.customers) {
            def customer = new Customer(
                    id: cust.id,
                    first_name: cust.first_name,
                    last_name: cust.last_name
            )
            customer.id = cust.id
            if (!Customer.exists(customer.id)) {
                customer.save(failOnError: true, flush: true)
            }
        }
    }

    def getProducts() {
        def parsedData = getShopifyData("products")
        for (def prod: parsedData.products) {
            def image_url = prod.image ? prod.image.src : ''
            def product = new Product(
                    id: prod.id,
                    title: prod.title,
                    image_url: image_url
            )
            product.id = prod.id
            if (!Product.exists(product.id)) {
                product.save(failOnError: true, flush: true)
            }
        }

    }

    def getShopifyData(data) {
        String shopifyUrl = "https://smuin.myshopify.com/admin/" + data + ".json"
        String key = "80c69701accbe27e0e7a784e32ce35ce"
        String password = "06a25574be4132ad2c33eb1d8103784a"
        String credentials = key + ":" + password
        String encodedCredentials = Base64.encoder.encodeToString(credentials.getBytes())
        URL url = new URL(shopifyUrl)
        URLConnection urlConnection = url.openConnection()
        urlConnection.setRequestProperty("Authorization", "Basic " + encodedCredentials)
        BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))
        JsonSlurper slurper = new JsonSlurper()
        return slurper.parse(input)
    }
}
