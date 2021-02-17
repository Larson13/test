var welcome = {

    data: {
        url: null,
        redriectUrl: null,
        loginUrl: null,
        userName: null
    },

    module: {

        init: function(){
            welcome.data.userName = $.getQueryString("userName");
            $("#welcomeText").html("欢迎您，"+welcome.data.userName);
            var type = $.getQueryString("type");
            if (type == 0){
                $("#myHome a").attr("href","/pinter/bank/page/welcome?userName="+welcome.data.userName+"&type="+type);
            }else if (type == 1){
                $("#myHome a").attr("href","/pinter/bank/page/welcome2?userName="+welcome.data.userName+"&type="+type);
            }
        },

        // 跳转
        jump: function (type) {
            if (type == 0){
                // 基于cookie的方式
                welcome.data.redriectUrl = "/pinter/bank/page/money?userName="+ welcome.data.userName + "&type=0";
            }else if (type == 1){
                // 基于token的方式
                welcome.data.redriectUrl = "/pinter/bank/page/money2?userName="+ welcome.data.userName + "&type=1";
            }
            window.location.href = welcome.data.redriectUrl;
        },

        logout: function (type) {
            if (type == 0){
                welcome.data.loginUrl = "/pinter/bank/page/login";
            }else if (type == 1){
                welcome.data.loginUrl = "/pinter/bank/page/login2";
            }
            $.ajax({
                url: "/pinter/bank/api/logout?userName="+welcome.data.userName,
                type: "GET",
                cache:false,
                async: false,
                dataType: "json",
                success: function(data){
                    if (data.code == 0){
                        window.location.href= welcome.data.loginUrl
                    }else {
                        alert(data.message);
                    }
                },
                error:function(err){
                    alert(err);
                }
            });
        }
    }

}
welcome.module.init();
