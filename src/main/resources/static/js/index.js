// 动态更新用户信息
function fetchUserStatus() {
    fetch('/user/profile')
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                // 未登录状态时重置链接
                throw new Error('未登录');
            }
        })
        .then(data => {
            const userLink = document.getElementById('user-menu-link');
            userLink.textContent = data.data.username;
            userLink.href = "./loading.html";  // 用户资料页面地址
        })
        .catch(error => {
            console.log('用户未登录，显示登录入口');
            const userLink = document.getElementById('user-menu-link');
            userLink.textContent = '登录/注册';
            userLink.href = './loading.html';
        });
}

// 页面加载时立即执行
document.addEventListener('DOMContentLoaded', fetchUserStatus);

//点击能查看图片
var clipboard = new ClipboardJS('.text');
images_zoom();

// 动态加载导航栏
fetch('navbar.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('navbar').innerHTML = data;
    });

// 修正跳转函数
function goToWallpaperIndex(event) {
    // 获取当前图片的URL
    const imageUrl = event.target.previousElementSibling.src;
    // 确认目标页面路径是否正确
    window.location.href = `wallpaper.html?background=${encodeURIComponent(imageUrl)}`; // 修改为实际存在的路径
}

// 事件委托绑定点击（解决动态内容问题）
document.getElementById('gallery').addEventListener('click', function(e) {
    if (e.target.classList.contains('hover-text')) {
        goToWallpaperIndex(e);
    }
});