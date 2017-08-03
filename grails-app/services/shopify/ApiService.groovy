package shopify

import grails.gorm.transactions.Transactional

@Transactional
class ApiService {

    def getOrders() {
        String shopifyUrl = "https://smuin.myshopify.com/admin/orders.json"
        String key = "80c69701accbe27e0e7a784e32ce35ce"
        String password = "06a25574be4132ad2c33eb1d8103784a"
        String credentials = key + ":" + password;
        String encodedCredentials = Base64.encoder.encodeToString(credentials.getBytes())
        URL url = new URL(shopifyUrl)
        URLConnection urlConnection = url.openConnection()
        urlConnection.setRequestProperty("Authorization", "Basic " + encodedCredentials)
        BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))
        String inputLine
        while((inputLine = input.readLine()) != null) {
            System.out.println(inputLine)
        }
        input.close()
    }
}
