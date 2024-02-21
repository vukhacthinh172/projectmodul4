/* let productList = [
    {
        name: "Bát ăn cơm",
        price: 420000,
        id: 1,
        img: "./assets/images/anhsp1.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 621,
    },
    {
        name: "Bát uống rượi",
        price: 520000,
        id: 2,
        img: "./assets/images/anhsp2.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 778,
    },
    {
        name: "Bát uống nước",
        price: 520000,
        id: 3,
        img: "./assets/images/anhsp3.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 560,
    },
    {
        name: "Cốc cocain",
        price: 620000,
        id: 4,
        img: "./assets/images/anhsp4.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 120,
    },
    {
        name: "Vẫn là cốc",
        price: 620000,
        id: 5,
        img: "./assets/images/anhsp5.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 26,
    },
    {
        name: "Cốc thủy tinh",
        price: 720000,
        id: 6,
        img: "./assets/images/anhsp6.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 7,
    },
    {
        name: "Giống bình hoa",
        price: 720000,
        id: 7,
        img: "./assets/images/anhsp7.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 15,
    },
    {
        name: "Vẫn là bình hoa",
        price: 120000,
        id: 8,
        img: "./assets/images/anhsp8.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 5,
    },
    {
        name: "Chắc giống cái trên",
        price: 120000,
        id: 9,
        img: "./assets/images/anhsp8.webp",
        text: "được làm từ chất liệu cao cấp từ những bàn tay lành nghề của người anh em láng giềng",
        stock: 3,
    }
]; */
//Quản lý user
renderUsers = () => {
    let users = JSON.parse(localStorage.getItem('users'));
    let text = '';
    for (let i = 0; i < users.length; i++) {
        if (users[i].role == 'active') {
            text += `<tr>
                <td>${i + 1}</td>
                <td>${users[i].yourEmail}</td>
                <td>${users[i].password}</td>
                <td>${users[i].id}</td>
                <td>${users[i].yourName}</td>
                <td>${users[i].role}</td>
                <td><button class="action btn" onclick = "popupBlock(${users[i].id})">Khóa người dùng</button></td>
            </tr>
            `
        } else {
            text += `<tr>
                <td>${i + 1}</td>
                <td>${users[i].yourEmail}</td>
                <td>${users[i].password}</td>
                <td>${users[i].id}</td>
                <td>${users[i].yourName}</td>
                <td>${users[i].role}</td>
                <td><button class="action btn btn" onclick = "active(${users[i].id})">Mở khóa người dùng</button></td>
            </tr>
            `
        }
        document.getElementById('tbody__users').innerHTML = text;
    }}
renderUsers();
let popupBlock = (userId) => {
    document.getElementsByClassName('popupBlock')[0].style.display = 'block';
    document.getElementsByClassName('blockUser')[0].setAttribute('onclick', `blockUser(${userId})`);
}
let blockUser = (userId) => {
    let users = JSON.parse(localStorage.getItem('users'));
    for (let i = 0; i < users.length; i++) {
        if (users[i].id == userId) {
            users[i].role = 'block';
            break;
        }
    }
    localStorage.setItem('users', JSON.stringify(users))
    cancelPopupUser();
    renderUsers();
}
let active = (userId) => {
    let users = JSON.parse(localStorage.getItem('users'));
    for (let i = 0; i < users.length; i++) {
        if (users[i].id == userId) {
            users[i].role = 'active';
            break;
        }
    }
    localStorage.setItem('users', JSON.stringify(users));
    cancelPopupUser();
    renderUsers();
}
let cancelPopupUser = () => {
    document.getElementsByClassName('popupBlock')[0].style.display = 'none'
}
let logOut = () => {
    let checkLogin = localStorage.getItem('adminId');
    let admin = JSON.parse(localStorage.getItem('admin'));
    localStorage.removeItem("id");
            window.location.href="../page/login.html"
}

// Quản lý product
let products = JSON.parse(localStorage.getItem('productList'));
let renderProducts = (productList) => {
    if (productList == undefined) {
        productList = [];
    }
    let text = '';
    for (let i = 0; i < productList.length; i++) {
        text += `
        <tr>
            <td>${i + 1}</td>
            <td><img src="${productList[i].img}" alt="item1"></td>
            <td>${productList[i].name}</td>
            <td>${productList[i].id}</td>
            <td>${productList[i].price}</td>
            <td>${productList[i].stock}</td>
            <td><button onclick="popupDelete(${productList[i].id})" class="btn">Xóa</button></td>
        </tr>
        `
    }
    document.getElementById('tbody__products').innerHTML = text;
}
renderProducts(products);
let popupDelete = (productId) => {
    document.getElementsByClassName('popupDelete')[0].style.display = 'block';
    document.getElementsByClassName('deleteItem')[0].setAttribute('onclick', `deleteItem(${productId})`);
}
// xóa sản phẩm
let deleteItem = (productId) => {
    let products = JSON.parse(localStorage.getItem('productList'))
    for (let i = 0; i < products.length; i++) {
        if (products[i].id == productId) {
            products.splice(i, 1)
            localStorage.setItem('productList', JSON.stringify(products));
            renderProducts(products);
            document.getElementsByClassName('popupDelete')[0].style.display = 'none'
        }
    }
}
let cancelPopupItem = () => {
    document.getElementsByClassName('popupDelete')[0].style.display = 'none'
}
//add item
//function lay src img
itemId = () => {
    return Math.floor(Math.random() * 10000)
}
let imgSrc = '';
let imageInput = document.getElementById('imageInput');
imageInput.addEventListener('change',(event)=>{
    let selectedFile = event.target.files[0];
    if(selectedFile){
        let reader = new FileReader();
        reader.onload = (i) => {
            imgSrc = i.target.result;
        };
        reader.readAsDataURL(selectedFile);

    }
});
// thêm sản phảm mới
let popupAddItem = () => {
    document.getElementsByClassName('addItem')[0].style.display = 'block';
}
// xóa sản phẩm
let cancelAddItem = () => {
    document.getElementsByClassName('addItem')[0].style.display = 'none';
}
// thêm sản phẩm
let addItem = () => {
    let products = JSON.parse(localStorage.getItem('productList'));
    let nameItem = document.getElementById('itemName').value;
    let priceItem = document.getElementById('itemPrice').value;
    let stockItem = document.getElementById('itemStock').value;
    let newItem = {
        id: itemId(),
        name: nameItem,
        price: priceItem,
        src: imgSrc,
        stock: stockItem,
    }
    products.push(newItem);
    localStorage.setItem('productList', JSON.stringify(products));
    document.getElementsByClassName('addItem')[0].style.display = 'none';
    renderProducts(products);
}