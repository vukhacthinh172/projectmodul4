
function login() {
    console.log(11);
    // lấy giá trị của ô input
    let emailAddress = document.getElementById("emailAddress").value;
    let password = document.getElementById("password").value;
    let checkItem = document.getElementsByClassName("form-control form-control-lg")
    for (let i = 0; i < checkItem.length; i++) {
        if (checkItem[i].value == "") {
            document.getElementsByClassName("noticeErroLogin")[i].style.display = "block";
        } else {
            document.getElementsByClassName("noticeErroLogin")[i].style.display = "none";

        }
    }
    if (emailAddress == "admin" && password == "admin") {
        localStorage.setItem("adminId", 1);
        window.location.href = "../page/admin.html"
    }else{

   
        // lấy dữ liệu từ local về :
        let users = JSON.parse(localStorage.getItem("users")) || [];
        for (let i = 0; i < users.length; i++) {
            if (users[i].yourEmail == emailAddress && users[i].password == password) {
                localStorage.setItem("userId", users[i].id);
                window.location.href = "../../../templates/user/index.html"
                return;
            }
        }
    }
    document.getElementById("loginErro").innerHTML = "Đăng nhập thất bại !!"
}

/* let email = document.getElementById("email").value;
let password = document.getElementById("password").value;

let admin= JSON.parse(localStorage.getItem("admin"))||[];
for(let i=0;i<admin.length;i++){
    if(admin[i].email==email&&admin[i].password==password){
        window.location.href = "../page/admin.html"
    }
}
    
 */
