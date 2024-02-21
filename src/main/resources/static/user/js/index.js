/* 
    render đổ danh sách sản phẩm bằng js
 */
//  function hiển thị danh sách sản phẩm
onload = (event) => {

}
let productListItem = [
    {
        name: "Bát ăn cơm",
        price: 420000,
        id: 1,
        img: "../assets/images/anhsp1.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 621,
    },
    {
        name: "Bát uống rượi",
        price: 520000,
        id: 2,
        img: "../assets/images/anhsp2.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 778,
    },
    {
        name: "Bát uống nước",
        price: 520000,
        id: 3,
        img: "../assets/images/anhsp3.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 560,
    },
    {
        name: "Cốc cocain",
        price: 620000,
        id: 4,
        img: "../assets/images/anhsp4.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 120,
    },
    {
        name: "Vẫn là cốc",
        price: 620000,
        id: 5,
        img: "../assets/images/anhsp5.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 26,
    },
    {
        name: "Cốc thủy tinh",
        price: 720000,
        id: 6,
        img: "../assets/images/anhsp6.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 7,
    },
    {
        name: "Giống bình hoa",
        price: 720000,
        id: 7,
        img: "../assets/images/anhsp7.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 15,
    },
    {
        name: "Vẫn là bình hoa",
        price: 120000,
        id: 8,
        img: "../assets/images/anhsp8.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 5,
    },
    {
        name: "Chắc giống cái trên",
        price: 120000,
        id: 9,
        img: "../assets/images/anhsp9.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 3,
    }
];
const VND = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
});
//localStorage.setItem('admin', JSON.stringify(admin))
// lưu sản phẩm trên local

let products = JSON.parse(localStorage.getItem("productList"));
if (products == null) {
    localStorage.setItem("productList", JSON.stringify(productListItem))
}
// render index
function renderProduct(productList) {
    if (productList == undefined) {
        productList = [];
    }
    let text = "";
    for (let i = 0; i < productList.length; i++) {
        text += `
         <div class="col-4  mt-3">
                    <img src="${productList[i].img}" class="card-img-top" alt="...">
                    <div class="card-body ">
                        <h5 class="card-title">${productList[i].name}</h5>
                        <p class="card-text">${productList[i].text} </p>
                        <p class="card-text">${productList[i].price} đ</p>
                        <button onclick="addToCart(${productList[i].id})" class="btn " style="width: 270px;">Add To Cart</button >
                    </div>
                </div>
            </div>
         `

    }
    document.getElementsByClassName("product-item")[0].innerHTML = text
}
renderProduct(products);
//localStorage.setItem("productList",JSON.stringify(productList))
showCount = () => {
    let checkLogin = localStorage.getItem("userId");
    let users = JSON.parse(localStorage.getItem("users"));
    if (checkLogin) {
        for (let i = 0; i < users.length; i++) {
            if (users[i].id == checkLogin) {
                //user[i].cart
                let count = 0;
                for (let j = 0; j < users[i].cart.length; j++) {
                    count += users[i].cart[j].quantity;
                }
                document.getElementsByClassName("count")[0].innerHTML = count;
                users[i].count = count;
                localStorage.setItem('users', JSON.stringify(users))
            }
        }
    }
}
showCount()
function addToCart(productId) {
    let checkLogin = localStorage.getItem("userId")
    console.log(checkLogin);
    let users = JSON.parse(localStorage.getItem("users"));
    // lấy toàn bộ danh sách sản phẩm
    let products = JSON.parse(localStorage.getItem("productList"));

    if (checkLogin) {
        // đã đăng nhập mới cho đi mua hàng
        // đi mua hàng dựa vào userId 
        // alert("đi mua hàng bình thường!")
        // mình có nhiều user thì phải lấy ra giỏ của user có id == checkLogin
        /* let cartUser=users.filter((item)=>{
            return item.id==checkLogin;
        })
        console.log("cartUser", cartUser); */
        for (let i = 0; i < users.length; i++) {
            if (users[i].id == checkLogin) {
                if (users[i].role == "block") {
                    alert("bạn đã bị khóa tài khoản khong thể mua hàng !!")

                } else {


                    // lấy ra giỏ hàng của user vừa đăng nhập
                    //users[i].cart
                    for (let j = 0; j < products.length; j++) {
                        if (products[j].id == productId) {
                            //... toán tử spread
                            // trước khi push phải xem sản phẩm đó đã có trong giỏ hàng hay chưa
                            // nếu có rồi thì tăng số lượng.
                            // chưa có thì push vào bình thường
                            // users[i].cart.push({ ...products[j],quantity:1 });
                            // localStorage.setItem("users",JSON.stringify(users));
                            let result = users[i].cart.filter((item) => {
                                return item.id == productId;
                            })
                            if (result.length == 0) {
                                users[i].cart.push({ ...products[j], quantity: 1 });
                                localStorage.setItem("users", JSON.stringify(users));
                                showCount();
                            } else {
                                // users[i].cart[j].quantity == ++users[i].cart[j].quantity;
                                // localStorage.setItem("users", JSON.stringify(users));
                                for (let k = 0; k < users[i].cart.length; k++) {
                                    if (users[i].cart[k].id == productId) {
                                        users[i].cart[k].quantity = ++users[i].cart[k].quantity;
                                        localStorage.setItem("users", JSON.stringify(users));
                                        showCount();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } else {
        // chưa đăng nhập không thể mua hàng
        alert("bạn phải đăng nhập để đi mua hàng!")
    }
}
let searchTimeout;

function searchCake() {
    clearTimeout(searchTimeout);
    searchTimeout = setTimeout(function () {
        let inputValue = document.getElementById("search").value;
        let result = products.filter((item) => {
            return item.name.indexOf(inputValue) !== -1;
        });

        if (result.length !== 0) {
            renderProduct(result);
        } else {
            renderProduct();
        }
    }, 300); // Đợi 300 milliseconds sau khi người dùng dừng gõ mới thực hiện tìm kiếm
}
let titlelogin = document.querySelector(".titlelogin");
let checkLogin = localStorage.getItem("userId");
let users = JSON.parse(localStorage.getItem("users"));
let user = users.filter(item => item.id == checkLogin)[0]
if (checkLogin) {
    titlelogin.innerHTML =
        `  <div class="p-2 flex-fill bd-highlight d-flex justify-content-end align-items-center ">
        <a  class="fst-italic fs-7 text-success-emphasis username"
            style="text-decoration: none;">Xin chào:${user.yourName}</a>
        <span class="m-4 titlelogin">
            <a  href="javascript:logout()"><span class="material-symbols-outlined">
                logout
            </span>
        </a>
        </span>
    </div>
    `
} else {
    titlelogin.innerHTML = `
        <span class="m-4 ">
                        <i class="fa-solid fa-circle-user fa-xl"></i>
                        <a class="fst-italic fs-7 text-success-emphasis username" href="./page/login.html"
                            style="text-decoration: none;"> Login</a>
                    </span>
        `
}
function logout() {
    console.log(11);
    let checkLogin = localStorage.getItem("userId");
    localStorage.removeItem("userId");
    window.location.href = "../page/login.html"
}
[].filter()