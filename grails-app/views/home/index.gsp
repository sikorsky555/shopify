<html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home Page</title>
</head>
<body>

<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Welcome to our Home Page!</h1>
    </section>
    <table>
        <tr>
            <th>Total Price</th>
            <td>Customer</td>
            <td>Products</td>
        </tr>
    <g:each in="${orderList}" var="order">
        <tr>
            <td>${order.total_price}</td>
            <td><g:if test="${order.customer}">${order.customer.first_name} ${order.customer.last_name}</g:if></td>
            <td>
                <g:each in="${order.line_items}" var="lineItem">
                    <img src="${lineItem.product.image_url}"
                         alt="${lineItem.product.title}"
                         title="${lineItem.product.title} x ${lineItem.quantity}"
                         style="max-height: 200px;"/>
                </g:each>
            </td>
        </tr>
    </g:each>
    </table>
    <form><input type="submit" formaction="/orders" value="fetch"></form>
    <form><input type="submit" formaction="/orders/delete" value="delete"></form>
</div>
</body>
</html>