//注册时提交表单执行请求
$('#form1').submit(function(event) {
    event.preventDefault(); // 阻止表单的默认提交行为

    // 获取表单数据
    var username = $('#username').val();
    var email=$('#email').val();
    var password=$('#password').val();

    // 判断是否有没有填写的
    if (username.trim()===''|| email.trim()===''|| password.trim()===''){
        alert('请填写所有必填字段哟（用户名、邮箱和密码）（-~-）！');
        return;
    }

    var formData = $(this).serialize(); // 获取表单数据

    $.ajax({
        type: 'POST',
        url: '/register', // 后端注册接口的URL
        data: formData,
        success: function(response) {
            // 注册成功后的处理逻辑
            if(response==='注册成功'){
                alert('欢迎和我一起漂泊，一起进入我的世界吧！');
                window.location.href = '../login.html'; // 跳转到登录页面
            }else {
                alert(response);
            }

        },
        error: function(xhr, status, error) {
            // 注册失败则显示错误提示
            alert(xhr.responseText); // xhr.responseText 是后端返回的错误消息
        }
    });
});