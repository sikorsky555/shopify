package shopify

class HelloController {

    def apiService

    def index() {
        render "Hello World!<form><input type=\"submit\" formaction=\"/orders\" value=\"fetch\"></form>"
    }
}
