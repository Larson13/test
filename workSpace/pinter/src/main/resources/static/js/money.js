var money = {

    data: {
        url: null,
        loginUrl: null,
        userName: null,
        type: null
    },

    module: {

        init: function () {
            money.data.userName = $.getQueryString("userName");
            money.data.type = $.getQueryString("type");
            $("#welcomeText").html("欢迎您，" + money.data.userName);
            var type = $.getQueryString("type");
            if (type == 0){
                $("#myHome a").attr("href","/pinter/bank/page/welcome?userName="+money.data.userName+"&type="+type);
            }else if (type == 1){
                $("#myHome a").attr("href","/pinter/bank/page/welcome2?userName="+money.data.userName+"&type="+type);
            }
            money.module.query(money.data.type)
        },

        // 查询余额
        query: function (type) {
            if (type == 0) {
                // 基于cookie的方式
                money.data.url = "/pinter/bank/api/query?userName=" + money.data.userName;
                money.data.loginUrl = "/pinter/bank/page/login";
            } else if (type == 1) {
                // 基于token的方式
                money.data.url = "/pinter/bank/api/query2?userName=" + money.data.userName;
                money.data.loginUrl = "/pinter/bank/page/login2";
            }

            $.ajax({
                url: money.data.url,
                type: "GET",
                cache: false,
                async: false,
                beforeSend: function(request) {
                    request.setRequestHeader("testfan-token", $("#token").val());
                },
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        $("#moneyNum").html("您的余额为：" + data.data);
                    } else {
                        alert(data.message);
                        window.location.href = money.data.loginUrl;
                    }
                },
                error: function (err) {
                    alert(err)
                }
            });

        },

        logout: function () {
            $.ajax({
                url: "/pinter/bank/api/logout?userName=" + money.data.userName,
                type: "GET",
                cache: false,
                dataType: "json",
                success: function (data) {
                    if (data.code == 0) {
                        window.location.href = money.data.loginUrl;
                    } else {
                        alert(data.message);
                    }
                },
                error: function (err) {
                    alert(err)
                }
            });
        }
    }
}
money.module.init();
