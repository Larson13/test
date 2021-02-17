//为了更好的兼容性，开始前有个分号
;(function ($) {    // 此处将$作为匿名函数的形参
    $.extend({
        //时间插件
        datePicker: function (selector, fn, dataConfig) {
            var date_config = {
                format: 'YYYY-MM-DD HH:mm:ss', // (string) date/time格式
                separator: "-",// (string) 分隔符
                singleDatePicker: false,
                parentEl: 'body',
                timePicker: false, // 是否显示time选择
                timePickerIncrement: 1,// time选择递增数单位分钟
                timePicker12Hour: false, // (boolean) 是否12小时制
                opens: 'right',// (string: 'left'/'right') 显示在元素左边还是右边
                startDate: moment().subtract(29, 'days'),// (Date object,// moment object or// string) 起始时间
                endDate: moment(),// (Date object, moment object or string)// 结束时间
                // minDate:null ,//(Date object, moment object or string) 可选最早时间
                // maxDate:null,//(Date object, moment object or string) 可选最迟时间
                buttonClasses: ['btn btn-default'],
                applyClass: 'btn-small btn-primary',
                cancelClass: 'btn-small',
                ranges: {},
                selectForward: true,
                selectBackward: true,
                singleDate: false,
                // showWeekNumbers: true,
                locale: {
                    applyLabel: '查询',
                    cancelLabel: '取消',
                    fromLabel: '开始',
                    toLabel: '结束',
                    customRangeLabel: '自定义',
                    daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
                    monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                    firstDay: 1
                }
            }
            if (dataConfig != null && dataConfig != undefined) {
                for (var i in dataConfig) {
                    date_config[i] = dataConfig[i];
                }
            }
            return {
                datePickerInit: $(selector).daterangepicker(date_config, fn),
                datePickerObj: $(selector).data('daterangepicker')
        }
        },
        // 封装ajax,url必填其他选填
        invoke: function (url, dataJson) {
            url = location.origin + "/" + (location.pathname.split("/")[1]) + url;
            $.ajax({
                url: url,
                data: $.isNullObj(dataJson.data) ? null : dataJson.data,
                dataType: $.isNullObj(dataJson.dataType) ? "json" : dataJson.dataType,
                type: $.isNullObj(dataJson.type) ? "POST" : dataJson.type,
                async: $.isNullObj(dataJson.async) ? true : false,
                success: function (data) {
                    $.commonMethodInvoke(dataJson.callback, data);
                },
                error: function (data) {
                    $.commonMethodInvoke(dataJson.callback, data);
                }
            })
        },
        // 判断对象是否为空
        isNullObj: function (obj) {
            if (typeof obj == "string") {
                return (obj == undefined || obj == null || obj == "" );
            } else {
                return (obj == undefined || obj == null);
            }
        },
        // 公共方法调用
        commonMethodInvoke: function (callback, data) {
            if (!$.isNullObj(callback)) {
                var methodCall = $.Callbacks("once");
                methodCall.add(callback);
                methodCall.fire(data);
                methodCall.remove(callback);
            }
        },

        /**
         * 拼接select的option data格式： { dataJson:[],//后台数据 isMulti：true
		 * //可选,多选框必填，树形下拉框选填 isGroup：true //可选,多选框必填，树形必填 }
         */
        generatorOpt: function (selector, data, fieldId, fieldName) {

            if ($.isNullObj(data.isMulti)) {
                /**
                 * 需要后台提供如下格式的json串 [{ id:0, name:"" }]
                 */
                //var opts = "<option value=''>--请选择--</option>";
                $.each(data.dataJson, function (i, n) {
                    opts += "<option value='" + n[fieldId] + "'>" + n[fieldName] + "</option>";
                })

                $(selector).html(opts);
            } else {
                if (!$.isNullObj(data.isGroup)) {
                    var opts = "";
                    /**
                     * 需要后台提供如下格式的json串-->java类型：List --List<Map<String,Map<String,Object>>>
                     * 可以查出来在业务层处理逻辑 [{ parentId:{。 parentName:"", children:[ {
					 * childId:0, childName:"" } ] } }]
                     *
                     */
                    $.each(data.dataJson, function (i, n) {
                        for (var i in n) {
                            opts += "<optgroup label='" + n[i].parentName + "'>";
                            $.each(n[i].children, function (j, k) {
                                opts += "<option value='" + i + "-" + k.id + "'>" + k.name + "</option>";
                            });
                            opts += "</optgroup>";
                        }
                    });
                    $(selector).html(opts);
                } else {
                    delete data["isMulti"];
                    $.generatorOpt(selector, data);
                }
            }
        },
        // 左右选择框
        // data中包含一个数组，数组名为selArr。数组存放两侧及按钮的选择器，依次是：左、右、中间全部向右、中间选中向右、中间选中向左、中间全部向左，必填.
        multiSelect: function (data) {
            $(data.selArr[0]).multisel({
                right: data.selArr[1],
                rightAll: data.selArr[2],
                rightSelected: data.selArr[3],
                leftSelected: data.selArr[4],
                leftAll: data.selArr[5],
                beforeMoveToRight: $.isNullObj(data.beforeMoveToRight) ? null : data.beforeMoveToRight,
                afterMoveToRight: $.isNullObj(data.afterMoveToRight) ? null : data.afterMoveToRight,
                beforeMoveToLeft: $.isNullObj(data.beforeMoveToLeft) ? null : data.beforeMoveToLeft,
                afterMoveToLeft: $.isNullObj(data.afterMoveToLeft) ? null : data.afterMoveToLeft
            });
        },
        // bootstrap-multi,获取组的选中值使用$(selector).val()可以的到一个数组
        multiSel4Bstp: function (selector, data) {
            var config = {
                templates: {
                    // button: '<button type="button" class="multiselect
                    // dropdown-toggle" data-toggle="dropdown"></button>',
                    ul: '<ul class="multiselect-container dropdown-menu" style="height:200px;overflow:auto"></ul>',
                    filter: '<li class="multiselect-item filter"><div class="input-group"><input class="form-control multiselect-search" type="text"></div></li>',
                    filterClearBtn: null,
                    // li: '<li><a
                    // href="javascript:void(0);"><label></label></a></li>',
                    // divider: '<li class="multiselect-item divider"></li>',
                    // liGroup: '<li class="multiselect-item group"><label
                    // class="multiselect-group"></label></li>'
                },
                includeSelectAllOption: true,
                enableFiltering: true,
                includeSelectAllOption: false,
                nonSelectedText: "----请选择----",
                allSelectedText: "全部选中",
                filterPlaceholder: "输入内容可搜素",
                nSelectedText: '个被选中',
                disabledText: '没有数据',// disabled时显示的文字说明
                enableClickableOptGroups: false,// 同时取组或者all
                enableCollapsibleOptGroups: true,// 组可折叠
                /* filterBehavior: 'text',//根据value或者text过滤 */
                enableFullValueFiltering: false,// 能否全字匹配
                enableCaseInsensitiveFiltering: true,// 不区分大小写
                selectAllNumber: true,// true显示allselect（6）,false显示allselect
                selectAllJustVisible: true,// 选择所有的。true为全选可见的
                disableIfEmpty: false,// 没有选项时readonly
                dropRight: false,
                maxHeight: 400,
                dropUp: false
            }

            if (!$.isNullObj(data)) {
                for (var i in data) {
                    config[i] = data[i];
                }
            }
            return $(selector).multiselect(config);

        },
        // 自动补全,若是
        autoComplete: function (selector, msg, config) {
            if ($.isNullObj(msg)) {
                return "data is error";
            }
            var choisedItems = {};
            var init_config = {
                source: msg,
                autoSelect: true,
                updater: function (item) {
                    /*choisedItems=item;*/
                    return item;
                },
                matcher: function (item) {
                    /*if(!$.isEmptyObject(choisedItems)){
                     if($.getSubArray(msg,choisedItems.id).length != 0){
                     this.setSource($.getSubArray(msg,choisedItems.id));
                     }

                     };*/
                    return item;
                }
            };
            if (!$.isNullObj(config)) {
                for (var i in config) {
                    init_config[i] = config[i];
                }
            }
            $(selector).typeahead(init_config);
        },
        //树形表格
        treeView: function (selector, data, conf) {
            var config = {
                data: data,
                levels: 1,
                showBorder: false,
                showCheckbox: true,
                /*multiSelect:true,*/
                onNodeUnchecked: function (event, node) {
                    $.commonMethodInvoke(conf.onNodeUnchecked, {
                        node: node,
                        event: event
                    })
                },
                onNodeChecked: function (event, node) {
                    $.commonMethodInvoke(conf.onNodeChecked, {
                        node: node,
                        event: event
                    })
                },
                onNodeSelected: function (event, node) {
                    $.commonMethodInvoke(conf.onNodeSelected, {
                        node: node,
                        event: event
                    })
                }
            }
            return $(selector).treeview(config);
        },
        //dataTable
        dataTable: function (selector, config) {
            var common_data = {
                ajax: {
                    url: "",
                    type: "POST",
                    data: {},
                    dataType: "json"
                },
                columns: {
                    fileds: [],
                    opts: []
                },
                iDisplayLength: 10,
                aaSorting: [],
                sort: false,
                info: false,
                processing: false,
                autoWidth: true,
                searching: false,
                lengthChange: false,
                sortCondition: "", //拼接sort条件
                callbackName: null, //列表加载完成执行的函数
                bPaginate: true,
                resultData:[],
                getColumns: function (data) {
                    var columns = [];
                    var m = 0;
                    if (!$.isEmptyObject(data) && !$.isNullObj(data.fields)) {
                        $.each(data.fields, function (i, n) {
                            var json = {};
                            if (n == null) {
                                json["data"] = null;
                                json["bSortable"] = false;
                                json = $.extend({}, json, data.opts[m]);
                                m++;
                            } else {

                                if (n.ableSort == undefined) {
                                    json["data"] = n;
                                    json["bSortable"] = false;
                                } else {
                                    json["data"] = n.data;
                                    json["bSortable"] = n.ableSort;
                                }

                            }
                            columns.push(json);
                        })
                    }

                    return columns;
                },
                valid: function () {
                    if (common_data.ajax.url == "") {
                        return false;
                    }
                    return true;
                },
                getRequestParam: null,
                callbackMethod: function (dt) {
                    var callback = $.Callbacks("once");
                    if (typeof common_data.callbackName === "function") {
                        callback.add(common_data.callbackName);
                        callback.fire(dt);
                    }
                }

            }
            if (!$.isNullObj(config)) {
                for (var i in config) {
                    common_data[i] = config[i];
                }
            }
            if(!common_data.bPaginate){
                common_data.getRequestParam=function(data){
                    return data;
                }
            }
            common_data.ajax.url = location.origin + "/" + (location.pathname.split("/")[1]) + common_data.ajax.url;
            //返回一个数据表格对象，进行一些动态操作
            return {
                dataTable: $(selector).DataTable({
                    ajax: function (data, callback, settings) {//ajax配置为function,手动调用异步查询
                        $.ajax({
                            type: common_data.ajax.type,
                            url: common_data.ajax.url,
                            cache: false, //禁用缓存
                            data: common_data.getRequestParam(common_data.ajax.data), //传入已封装的参数
                            dataType: common_data.ajax.dataType,
                            success: function (res) {

                                //封装返回数据，这里仅演示了修改属性名
                                var returnData = {};

                                if (common_data.bPaginate) {
                                    returnData.data = res.result.list == null ? [] : res.result.list ;
                                    common_data.resultData=res.result.list == null ? [] : res.result.list;
                                    returnData.recordsTotal = res.result.totalNums;
                                    returnData.recordsFiltered = res.result.totalNums;//后台不实现过滤功能，每次查询均视作全部结果
                                }else{
                                    returnData.data = res.result;
                                    common_data.resultData=res.result;
                                }
                                callback(returnData);
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                alert("查询失败");
                            }
                        });
                    },
                    processing: common_data.processing,
                    serverSide: true,
                    searching: common_data.searching,
                    bAutoWidth: common_data.autoWidth,
                    bLengthChange: common_data.lengthChange,
                    bInfo: common_data.info,
                    bSort: common_data.sort,
                    bPaginate: common_data.bPaginate,
                    columns: common_data.getColumns(common_data.columns),
                    sPaginationType: "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                    oLanguage: {
                        sLengthMenu: "每页显示 _MENU_条",
                        sZeroRecords: "没有找到符合条件的数据",
                        sProcessing: "&lt;img src=’./loading.gif’ /&gt;",
                        sInfo: "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                        sInfoEmpty: "记录为空",
                        sInfoFiltered: "(从 _MAX_ 条记录中过滤)",
                        sSearch: "搜索：",
                        oPaginate: {
                            "sFirst": "首页",
                            "sPrevious": "前一页",
                            "sNext": "后一页",
                            "sLast": "尾页"
                        }
                    },
                    fnServerParams: function (aoData) {
                    },
                    fnInitComplete: function (dt) {
                        //可以写jQuery方法，不能写$(function(){})
                        common_data.callbackMethod(dt);
                    },
                    fnPreDrawCallback: function (dt) {
                        //验证数据
                        return common_data.valid();
                    },
                    fnDrawCallback:function(dt){
                        if(!$.isNullObj(common_data.fnDrawCallback)){
                            common_data.fnDrawCallback(dt);
                        }
                    }

                }),
                dataTableConfig: common_data
            }

        },
        //根据主键取值array
        getJsonObj: function (data, id) {
            var obj = {};
            $.each(data, function (i, n) {
                obj[n[id]] = n;
            });
            return obj;
        },
        //获取一个树形JSON，使用树形菜单时用
        /*var data=[{
         id:"00001",
         text:"xx系统",
         nodes:[{
         text: "xx应用1",
         id: '00004',
         nodes:[{
         text: '版本号1',
         id: '00005'
         }]
         }]
         }];*/
        getTreeJson: function (data, parentId) {
            var treeArray = [];
            if ($.isNullObj(data)) {
                return null;
            }
            if ($.isNullObj(parentId)) {
                parentId = 0; //视具体业务而定
            }
            $.each(data, function (i, n) {
                var treeObj = {};
                if (n.parentId == parentId) {
                    treeObj["id"] = n.id; //视具体业务而定
                    treeObj["text"] = n.name; //视具体业务而定
                    //递归遍历
                    var subTreeArray = $.getTreeJson(data, n.id);
                    if (!$.isNullObj(subTreeArray) && subTreeArray.length != 0) {
                        treeObj["nodes"] = subTreeArray;
                    }
                    treeArray.push(treeObj);
                }
            });
            return treeArray;
        },
        //获取子数组,三级联动时可以使用，自动补全的时候也可以使用
        getSubArray: function (data, id) {
            var subArray = [];
            $.each(data, function (i, n) {
                var treeObj = {};
                if (n.parentId == id) {
                    subArray.push(n);
                }
            });
            return subArray;
        },
        //表单转json
        serializeObject: function (data) {
            var o = {};
            $.each(data, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        },
        //全选/反选
        choiseAll: function (selector, selectored) {
            var $this = $(selectored).find("input[type='checkbox']");
            if ($(selector).prop("checked")) {
                $($this).prop("checked", true);
            } else {
                $($this).prop("checked", false);
            }
        },
        //获取选中的复选框的字符串
        getCheckboxValues: function (selector) {
            var ids = "";
            $.each($(selector).find("input[type='checkbox']"), function (i, n) {
                if ($(n).prop("checked")) {
                    ids += "," + $(n).val();
                }
            });
            return $.isNullObj(ids) ? "" : ids.trim().substr(1);
        },
        //获取URL参数
        getQueryString:function(id) {
            var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            return $.isNullObj(r) ? null : r[2];
        }
    })
    /* 编写代码，可以使用$作为jQuery的别名 */
})(jQuery);    // 将jQuery作为实参传递给匿名函数
