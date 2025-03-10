// 当表单提交时执行登录请求(Ajax)
$('#loginForm').submit(function(event) {
    event.preventDefault(); // 阻止表单的默认提交行为
    var formData = $(this).serialize(); // 获取表单数据

    $.ajax({
        type: 'POST',
        url: '/login', // 后端登录接口的URL
        data: formData,
        success: function(response) {

            // 登录成功则重定向回index.html,这一步可以在前端完成
            window.location.href = '/';
            // JSON数据输出区域
            // console.log('登录成功:', response);
            // 更换顶部头像的图片地址
            $('#topAvatar').attr('src', './images/new_avatar.png');
        },
        error: function(xhr, status, error) {
            // 登录失败则显示错误提示
            alert(xhr.responseText); // xhr.responseText 是后端返回的错误消息
        }
    });
});