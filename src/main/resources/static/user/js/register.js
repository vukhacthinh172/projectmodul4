let  admin = [{
    yourEmail:"admin" ,
    password: "admin",
    id: 1,
}]
localStorage.setItem('users', JSON.stringify(admin));

// tạo Id ngẫu nhiên 
function userId() {
    return Math.floor(Math.random() * 6262655)
}
/* tạo function đăng kí */
let users = JSON.parse(localStorage.getItem("users")) || [];
// lấy dữ liệu từ local về mảng 
function register() {
    let yourName = document.getElementById("yourName").value;
    let yourEmail = document.getElementById("yourEmail").value;
    let password = document.getElementById("password").value;
    let repeatPassword = document.getElementById("repeatPassword").value;
    // tạo mảng chưa đối tượng người dùng vừa nhập :
    let user = {
        yourName: yourName,
        yourEmail: yourEmail,
        password: password,
        repeatPassword: repeatPassword,
        role : 'active',
        id: userId(),
        cart: [],

    }
    let checkItem = document.getElementsByClassName("form-control")
    for(let i=0;i<checkItem.length;i++){
           if(checkItem[i].value==""){
              document.getElementsByClassName("noticeErro")[i].style.display="block";
           }else{
            document.getElementsByClassName("noticeErro")[i].style.display="none";

           }
    }
    // check xem email đã tồn tại trong local chưa :
    let check = users.filter((item) => {
        return item.yourEmail == yourEmail;
    })
    if (check.length == 0) {
        // nếu email chưa có trong mảng 
        users.push(user);
        //lưu vào local 
        localStorage.setItem("users", JSON.stringify(users));
        // chuyển qua trang login
        window.location.href = "./login.html"
    } else {
        //nếu email đã tồn tại thì thông báo dòng bên dưới
        document.getElementsByClassName("noticeErro").innerHTML = "Email đã tồn tại !!"
    }
}
