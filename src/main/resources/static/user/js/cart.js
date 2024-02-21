const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
})
function renderCart() {
    let checkLogin = localStorage.getItem("userId");
    let users = JSON.parse(localStorage.getItem("users"));
    if (checkLogin != null) {
        for (let i = 0; i < users.length; i++) {
            if (users[i].id == checkLogin) {
                //cart user[i].cart
                totalMoney(users[i])
                let total = 0;
                let text = "";
                for (let j = 0; j < users[i].cart.length; j++) {
                    total += users[i].cart[j].price * users[i].cart[j].quantity;
                    text +=
                        `
                        <tr>
                        <td>${j + 1}</td>
                        <td>
                            <img src="${users[i].cart[j].img}">
                        </td>
                        <td>${users[i].cart[j].id}</td>
                        <td> ${users[i].cart[j].name} </td>
                        <td>${VND.format(users[i].cart[j].price)}</td>
                        <td>
                            <button  type="button" class="btn btn-outline-secondary" onclick="decrease(${users[i].cart[j].id})">-</button>
                                    ${users[i].cart[j].quantity}
                            <button  type="button" class="btn btn-outline-secondary" onclick="increase(${users[i].cart[j].id})">+</button>
                        </td>
                        <td>${VND.format(users[i].cart[j].price * users[i].cart[j].quantity)}</td>
                        <td> <button  type="button" class=" btn btn-outline-danger"  onclick="deleteProduct(${users[i].cart[j].id})">xoá </button></td>
                    </tr>
                        `
                }

                document.getElementById("tbody").innerHTML = `${text}`
            }
        }
    }
}
renderCart()
function increase(productId) {
    let checkLogin = localStorage.getItem("userId");
    let users = JSON.parse(localStorage.getItem("users"))
    if (checkLogin != null) {
        for (let i = 0; i < users.length; i++) {
            if (users[i].id == checkLogin) {
                for (let j = 0; j < users[i].cart.length; j++) {
                    if (users[i].cart[j].id == productId) {
                        users[i].cart[j].quantity = ++users[i].cart[j].quantity;
                        localStorage.setItem("users", JSON.stringify(users));
                        renderCart()
                    }

                }
            }

        }

    }
}
function decrease(productId) {
    let checkLogin = localStorage.getItem("userId");
    let users = JSON.parse(localStorage.getItem("users"));
    if (checkLogin != null) {
        for (let i = 0; i < users.length; i++) {
            if (users[i].id == checkLogin) {
                for (let j = 0; j < users[i].cart.length; j++) {
                    if (users[i].cart[j].id == productId && users[i].cart[j].quantity > 1) {
                        users[i].cart[j].quantity = --users[i].cart[j].quantity;
                        localStorage.setItem("users", JSON.stringify(users));
                        renderCart()
                        return;
                    }

                }
                alert('Quantity min is 1');
            }
        };

    }
}
function deleteProduct(productId) {
    console.log(11);
    let checkLogin = localStorage.getItem("userId");
    let users = JSON.parse(localStorage.getItem("users"));
    if (checkLogin != null) {
        for (let i = 0; i < users.length; i++) {
            if (users[i].id == checkLogin) {
                for (let j = 0; j < users[i].cart.length; j++) {
                    if (users[i].cart[j].id == productId) {
                        // Xóa sản phẩm khỏi giỏ hàng bằng cách cắt nó ra khỏi mảng
                        users[i].cart.splice(j, 1);
                        localStorage.setItem("users", JSON.stringify(users));
                        renderCart();
                        break; // Dừng vòng lặp sau khi xóa sản phẩm
                    }
                }
            }
        }
    }
}
function totalMoney(user) {
    if (user.cart.length != 0) {
        let total = 0;
        for (let i = 0; i < user.cart.length; i++) {
            total += user.cart[i].quantity * user.cart[i].price;
            console.log(total);
        }
        document.getElementById("total").textContent = `Total:${total.toLocaleString('vi-VN')}đ`
    }
}