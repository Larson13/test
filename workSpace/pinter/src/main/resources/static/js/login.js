var login = {

    data: {
        url: null,
        redriectUrl: null,
        userName: null,
        password: null
    },

    module: {
        // 初始化
        init: function(){},

        // 登录操作
        login: function (type) {
            login.data.userName = $("#userName").val();
            login.data.password = $("#password").val();
            if (type == 0){
                // 基于cookie的方式
                login.data.url = "/pinter/bank/api/login";
                login.data.redriectUrl = "/pinter/bank/page/welcome?userName="+login.data.userName+"&type=0";
            }else if (type == 1){
                // 基于token的方式
                login.data.url = "/pinter/bank/api/login2";
                login.data.redriectUrl = "/pinter/bank/page/welcome2?userName="+login.data.userName+"&type=1";
            }

            $.ajax({
                url: login.data.url,
                type: "POST",
                data:'userName='+login.data.userName+'&password='+login.data.password,
                cache:false,
                async: false,
                dataType: "json",
                success: function(data){
                    if (data.code == 0){
                        window.location.href = login.data.redriectUrl;
                    }else {
                        alert(data.message);
                    }
                },
                error:function(err){
                    alert(error)
                }
            });

        }

    }

}
