//localStorage.clear();
//localStorage.schedules = "{}";
//localStorage.plans = "{}";
ITEM_NORMAL = 0;
ITEM_OPERATION = 1;
ITEM_INJECTION = 2;
SCHEDULE_LIST = 0;
PLAN_LIST = 1;
C_TYPE_SCHEDULE = 0;
C_TYPE_PLAN = 1;
var host_url = "https://localhost/coco";
var coco = new CoCo();
//所有计划JSON
var allPlans = eval("("+localStorage['plans']+")");
var completedPlans = eval("("+localStorage['completedPlans']+")");
var schedules = "";
var markers = {};
var user_locate = {};
var currentPlan = {};
var importType;
var animationMap = {};
var copySchedules = [];

function CoCo()
{
	this.name = "big pig";
	this.age = 20; 
	this.standing;
	this.bust;
	this.waist;
	this.getDayPlans = function(fmtDate){
		//$.getJSON(host_url + "/getPlanByDate.do", { "fmtDate" : fmtDate}, function(json){
			//console.debug(json.res);
			//console.debug(json.res["a"]);
		//});
		/*if(this.isEmpty(allPlans[fmtDate]))
			return null
		return allPlans[fmtDate];*/
	}
	this.savePlan = function(data, callback){
		$.post(host_url+"/savePlan.do", {data : data},
			function(redata){
	        console.log(redata);
	        if(redata.data){
	        	for(i = 0 ; i < redata.data.scheduleList.length; i++){
					var newPlan = redata.data.scheduleList[i];
					newPlan.className = "CoCo.Plan";
					if(newPlan.address){
						newPlan.addressid = newPlan.address.id;
						newPlan.endPointLng = newPlan.address.lng;
						newPlan.endPointLat = newPlan.address.lat;
						newPlan.address = newPlan.address.name;
	        		}
				}
	        }
	        callback(redata.data);
	        }, "json");
	}
	this.getPlan = function(currentDayFmt, callback){
		console.log("getPlan reqdata:");
		console.log({ planDate : currentDayFmt});
		console.log({data : { planDate : currentDayFmt.replace(/-/g, "")}});
		$.post(host_url+"/getPlanByDate.do", {data : JSON.stringify({ planDate : currentDayFmt.replace(/-/g, "")})},
			function(redata){
	        console.log(redata);
	        for(planName in redata.data){
		        if(redata.data[planName].scheduleList){
		        	for(i = 0 ; i < redata.data[planName].scheduleList.length; i++){
						var newPlan = redata.data[planName].scheduleList[i];
						newPlan.className = "CoCo.Plan";
						if(newPlan.address){
							newPlan.addressid = newPlan.address.id;
							newPlan.endPointLng = newPlan.address.lng;
							newPlan.endPointLat = newPlan.address.lat;
							newPlan.address = newPlan.address.name;
		        		}
					}
		        }
	        }
	        callback(redata.data);
	        }, "json");
	}
	
	//按planid删除计划列表
	this.deletePlanList = function(planid, callback){
		console.log("deletePlan reqdata:");
		console.log({ id : planid});
		$.post(host_url+"/deletePlan.do", {data : JSON.stringify({ id : planid})},
		function(redata){
	        console.log("redata :" + redata);
	        //callback(redata.data);
        }, "json");
	}
	
	//获取用户位置
	this.getLocate = function(userid, callback){
		console.log("getLocate reqdata:");
		$.get(host_url+"/wx/getUserLocate.do", {data : JSON.stringify({ userid : userid})},
		function(redata){
	        console.log("redata :" + redata);
	        if(redata.data){
		        var point = new BMap.Point(redata.data.lng, redata.data.lat);
		        var newMarker = new BMap.Marker(point);  // 创建标注
		        newMarker.setIcon(scheduleIcon);
				newMarker.disableMassClear();
				newMarker.setIcon(new BMap.Icon("img/motox48.png", 48));
				newMarker.setTitle("this is xaj");		      		       
		        map.removeOverlay(user_locate["xaj"]);
		        user_locate["xaj"] =newMarker ;
		        map.addOverlay(newMarker);
		        console.log(map.getOverlays());
		        //callback(redata.data);
	        }
        }, "json");
	}
	this.getPlanId = function(plans){
		var currentId = 1;
		if(!plans)
			return "plan_" + currentId;
		for(planId in plans){
			currentId++;
		}
		return "plan_" + currentId;
	}
	this.initSchedules = function() {
		console.debug("localStorage : "+localStorage["schedules"]);			
		console.debug("planStorage : "+ allPlans);
		if(localStorage.schedules == null || localStorage.schedules == undefined){
			localStorage.schedules = "{}";
			return [];
		}else if(localStorage.schedules == "" ){
			return [];
		}
		console.debug("initSchedules");
		return JSON.parse(localStorage.schedules);
	}
	this.initPlans = function(target){
		if(localStorage.plans == null || localStorage.plans == undefined){
				localStorage.plans = "{}";
		}
		else{
			console.debug("init currentPlan : " + allPlans[currentDayFmt]);
			if(allPlans[currentDayFmt] == null || allPlans[currentDayFmt] == undefined || this.isEmpty(allPlans[currentDayFmt][target]))
				return {};
			console.debug("initCurrentPlans");
			return allPlans[currentDayFmt][target];
		}		
	}
	this.Marker = function(name, bmapMaker){
		this.name = name;
		this.marker = bmapMaker;
		this.showMarkerInList =function(list){
			list.find("input[name='telephone']")
		}
	}
	this.accessTaobao = function(text, C_TYPE){
		// var titleReg = /[0-9]{1,3}\..*[0-9]{1,3}\./mg;
		// var nameReg = /\n[\u4e00-\u9fa5]{2,4}(,|，)/mg;
		// var infoReg = /.+([0-9]|[0-9]{6})$/mg;
		var schedules = [];
		var updateDate = (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss");
		var splitMarkReg = /,+|，+/;
		var noReg = /\b[0-9]+(?=\.)/;
		var noneReg = /^\n\s*$/;
		var phoneGroupReg = /^[0-9]+-?[0-9]+$/;
		var titleReg = /\S+/;
		var txtGroup = text.split("\r");
		var schedulesCount = 0;
		var wrongRow;
		try{
			for (var i = 0,j = 1,scheduleJSON;i < txtGroup.length; i++) {
			var strElement = txtGroup[i];
			if (noneReg.test(strElement)) {
				if(j == 3){
					//console.debug(scheduleJSON);
					//coco.onReadTaobaoTxt(scheduleJSON);//调用createSchedule
					schedulesCount++;
					j = 0;
				}
			}else if(j != 2){
				j = 1;
			}
			switch(j){
				case 0:
					break;
				case 1:
					var scheduleJSON = {};
					if(C_TYPE == C_TYPE_SCHEDULE){
						scheduleJSON = new CoCo.Schedule();
					}else if(C_TYPE == C_TYPE_PLAN){
						scheduleJSON = new CoCo.Plan();
					}else{
						console.error("wrong schedule type !");
					}
					scheduleJSON.no = strElement.match(noReg)[0];
					scheduleJSON.title = strElement;
					j++;
					break;
				case 2:
					var strElement2 = strElement.split(splitMarkReg);
					wrongRow = strElement2;
					scheduleJSON.name = strElement2[0].match(".+")[0];
					var z = 1
					for(var telephone = ""; z < strElement2.length; z++){
						if(phoneGroupReg.test(strElement2[z])){
							telephone += telephone == "" ? strElement2[z]:"，"+strElement2[z];
						}else{
							scheduleJSON.telephone = telephone;
							break;
						}
					}
					scheduleJSON.address = strElement2[z];
					schedules.push(scheduleJSON);
					j++;
					break;
			}
			}
		}catch(e){
			throw wrongRow;
			//console.error(wrongRow);
		}
		schedulesCount++;
		backupJSON = {"operatorName" : "手术", "updateDate" : updateDate, "schedulesCount" : schedulesCount, "scheduleList" : schedules};
		console.debug(backupJSON);
		return backupJSON;
	}
	this.getReader = function(files, code){
		if(files.length)
		{
			var file = files[0];
			var reader = new FileReader();
			reader.readAsText(file, code);
			return reader;
		}
	}
	this.export2txt = function(name){
		var backupJSON = {};
		var schedules = JSON.parse(localStorage[name]);
		backupJSON.updateDate = (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss");
		console.debug(backupJSON.updateDate);
		backupJSON.schedules = localStorage[name];
		backupJSON.schedulesCount = schedules.length;
		var blob = new Blob([JSON.stringify(backupJSON)], {type: "text/plain;charset=utf-8"});
		saveAs(blob, "coco-"+(new Date()).pattern("yyyy-MM-dd hh:mm")+".txt");
	}
	this.export2taobaoTxt = function(name, planDate){
		var result = "";
		var schedules;
		if(name == "plan"){
			schedules = allPlans[planDate]["scheduleList"];
		}
		else if(!localStorage[name]){
			return "没有数据";
		}else{
			schedules = JSON.parse(localStorage[name]);
		}
		updateDate = (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss");
		console.debug(updateDate);
		for(var i = 0; i < schedules.length; i++){
			var schedule = schedules[i];
			result += schedule.title+"\t" + schedule.distance + "\r\n";
			result += schedule.name + "，"+ schedule.telephone + "，，"+schedule.address + "\r\n\r\n";
		}
		var blob = new Blob([result], {type: "text/plain;charset=utf-8"});
		saveAs(blob, "coco-"+updateDate+".txt");
	}
	this.isEmpty = function(s){
		if(s == undefined || s == "" || s == null)
			return true
		return false;
	}
}

CoCo.Item =function(){
	this.price = 0;
}

function Pet(){
	this.breed;
	this.sex;
	this.age;
	this.weight;
}

CoCo.Operation = function(target){
	CoCo.Item.call(this);
	this.pets = [];
	this.getCount = function(){
		return this.target.length
	}
}
CoCo.Operation.prototype = new CoCo.Item();

CoCo.Injection = function(){
	CoCo.Item.call(this);
	this.vaccines = [];
	this.getCount = function(){
		return this.vaccines.length;
	}
}
CoCo.Injection.prototype = new CoCo.Item();
function ItemList(){
	this.operation;
	this.injection;
	this.goods;
}
//schedule
CoCo.Schedule = function(){
	this.no;
	this.title;
	this.name;
	this.telephone;
	this.address;
	this.distance;
	this.duration;
	this.endPointLng;
	this.endPointLat;
	this.type;
	this.status;
	this.itemList;
	this.className = "CoCo.Schedule";
}

CoCo.Plan = function(){
	CoCo.Schedule.call(this);
	this.time = new Date();
	this.className = "CoCo.Plan";
}
CoCo.Plan.prototype = new CoCo.Schedule();
CoCo.Plan.prototype.constructor = CoCo.Plan;

CoCo.Animation = function(id, exec, exec2){
	this.id = id;
	this.exec = exec;
	this.isActive;
	this.run = function(){
		console.log("start run");
		this.isActive = true;
	}();
	this.stop = function(){		
		this.isActive = false;
		exec2();
	}
}

CoCo.extends_plan = function(rowNo, tr, planData){
	tr.removeClass("planTemplate");
	var detailModal = $(".detailModal").clone();
	if(planData && planData.items){
		planData.items.forEach(function(ele){
			var li = $("<li>");
			li.append("<span>"+ele.price+"</span>");
		});
	}		
	//编辑备注
	$(tr).find(".btn-edit-remarks").popover({html : true, title : "备注",
		container : "body",
		content : detailModal.html(),
		trigger : "click"
	});
	//计算路程
	$(tr).find("input[name='address']").on("change", function(){
		console.log("changeAddress");
		$(this).parents("tr").find("input[name='distance']").val("");
		$(this).parents("tr").find("input[name='duration']").val("");
		$(this).parents("tr").find("input[name='endPoint-lng']").val("");
		$(this).parents("tr").find("input[name='endPoint-lat']").val("");
		/*if(planData){
			delete currentPlan[planData.boid].scheduleList[rowNo-1].distance;
			delete currentPlan[planData.boid].scheduleList[rowNo-1].duration;
			delete currentPlan[planData.boid].scheduleList[rowNo-1].endPointLng;
			delete currentPlan[planData.boid].scheduleList[rowNo-1].endPointLat;
		}*/
	});
	var local_bar = $(".planPanel .search-bar-template").clone();
	local_bar.removeClass("search-bar-template");
	local_bar.css("width", "180px");
	local_bar.addClass("search-bar");
	local_bar.find(".btn-search").on("click", function(){
		local.search(local_bar.find('input[name="search"]').val());
	});
	local_bar.find(".confirm").on("click", function(){
		if(local_bar.find("input[name='endPoint-lng']").val() == "")
			return false;
		$(tr).find("input[name='endPoint-lng']").val(local_bar.find("input[name='endPoint-lng']").val());
		$(tr).find("input[name='endPoint-lat']").val(local_bar.find("input[name='endPoint-lat']").val());
		if(markers["p"+planData.no])
			markers["p"+planData.no].setPosition(new BMap.Point(local_bar.find("input[name='endPoint-lng']").val(), local_bar.find("input[name='endPoint-lat']").val()));
		else
			markers["p"+planData.no] = new BMap.Point(local_bar.find("input[name='endPoint-lng']").val(), local_bar.find("input[name='endPoint-lat']").val());
		$(tr).find(".location").popover("hide");
		//markers["p"+planData.no] = relocaMarker;
	});		
	local_bar.find(".cancel").on("click", function(){
		$(tr).find(".location").popover("hide");
	});
	$(tr).find(".location").popover({html : true, title : "定位",
		content : local_bar,
		trigger : "manual"
	});
	$(tr).find(".location").on("click", function(){
		$(tr).find(".location").popover("toggle");
	});
	$(tr).find(".location").on("show.bs.popover", function(){
		$(".planPanel .location").each(function(index, ele){
			if(rowNo != index){
				$(this).popover("hide");
				local_bar.find("input").val("");
			}
		});
	});			

	//填充数据
	if(planData == null)
		return;
	tr.find("input[name='id']").val(planData.id);
	tr.find("input[name='no']").val(planData.no);
	tr.find("input[name='title']").val(planData.title);
	tr.find("input[name='name']").val(planData.name);
	tr.find("input[name='telephone']").val(planData.telephone);
	tr.find("input[name='address-id']").val(planData.addressid);
	tr.find("input[name='address']").val(planData.address);
	tr.find("input[name='distance']").val(planData.distance);
	tr.find("input[name='duration']").val(planData.duration);
	tr.find("input[name='remarks']").val(planData.remarks);
	tr.find("input[name='endPoint-lng']").val(planData.endPointLng);
	tr.find("input[name='endPoint-lat']").val(planData.endPointLat);
	tr.find("input[name='status']").val(planData.status);
	if(coco.isEmpty(planData.endPointLng)){
		tr.find(".location").removeClass("btn-success");
		tr.find(".location").addClass("btn-danger");
	}else if(coco.isEmpty(planData.distance)){
		tr.find(".location").removeClass("btn-danger");
		tr.find(".location").addClass("btn-warning");
	}

	//点击条目
	tr.find(".btn-select-schedule").on("click", function(event){
		var tr = $(this).parents("tr");
		var checkBox = tr.find(".btn-select-schedule .glyphicon");
		var no = tr.find('input[name="no"]').val();
		if(tr.find("input[name='isSelect']").val() == 0){
			tr.find("input[name='isSelect']").val(1);
			checkBox.removeClass("glyphicon-unchecked");
			checkBox.addClass("glyphicon-check");
			tr.addClass("btn-primary");
			if(markers["p"+no])
				markers["p"+no].setAnimation(BMAP_ANIMATION_BOUNCE);
		}else{
			tr.find("input[name='isSelect']").val(0);
			checkBox.removeClass("glyphicon-check");
			checkBox.addClass("glyphicon-unchecked");
			tr.removeClass("btn-primary");
			if(markers["p"+no])
				markers["p"+no].setAnimation(null);
		}
	});
	//点击回退
	tr.find(".btn-res-schedule").on("click", function(event){
		console.debug("restorePlan tr");
		var tr = $(this).parents("tr");
		var no = tr.find('input[name="no"]').val();
		restorePlan($(this));
		tr.remove();
		if(markers["p"+no]){
			markers["p"+no].setIcon(scheduleIcon);
			markers["p"+no].disableMassClear();
			return false;
		}
		return false;
	});
	//点击清除
	tr.find(".btn-del-schedule").on("click", function(event){
		console.log("deletePlan tr");
		var tr = $(this).parents("tr");
		var no = tr.find('input[name="no"]').val();
		tr.fadeOut("middle", function(){
			tr.remove();
			if(markers["p"+no]){
				delete markers["p"+no].setAnimation(null);
				return false;
			}
		});
		return false;
	});

	//快捷键复制
	// tr.on("keyup", function(event){
	// 	console.log(event.keyCode);
	// 	console.log(event.ctrlKey);
	// 	if(event.ctrlKey && event.keyCode == 67){
	// 		event.originalEvent.preventDefault();
	// 		console.log("yes");
	// 		$("#planList tr input[name='isSelect'][value='1']").parents("tr").each(function(index, ele){
	// 			var copyJSON = {};
	// 			copyJSON.element = $(ele).clone(true);
	// 			copyJSON.data = currentPlan.scheduleList[parseInt($(ele).find(".rowNo button").text()) - 1];
	// 			copySchedules.push(copyJSON);
	// 		});
	// 		console.log(copySchedules);
	// 	}
	// });
}

CoCo.uploadPlans = function(data){
	$.ajax(function(){
		
	});
}

CoCo.dragScheduleBind = function(schedule, aggregate){
	function allowDrop(ev){
		ev.preventDefault();
	}

	function drag(ev){
		console.info($(ev.currentTarget).parents("tbody").attr("id"));
		var tr = $(ev.currentTarget).parent()[0];
		ev.dataTransfer.setDragImage(tr ,10, 10);
		ev.dataTransfer.setData("startNo", $(ev.currentTarget).parent().find(".rowNo button").text());
		ev.dataTransfer.setData("startList", $(ev.currentTarget).parents("tbody").attr("id"));
	}

	function drop(ev){
		var startNo =ev.dataTransfer.getData("startNo");
		var endNo = $(ev.currentTarget).find(".rowNo button").text();
		var startList = ev.dataTransfer.getData("startList");
		var endList = $(ev.currentTarget).parents("tbody").attr("id");
		console.log($(ev.currentTarget));
		if(startList == "scheduleList" && endList == "planList"){
			ev.dataTransfer.dropEffect = 'none'; 
			console.log("joinPlan");
			return false;
		}
		ev.stopPropagation();
		if(coco.isEmpty(startNo) || coco.isEmpty(endNo) || startNo == endNo)
			return false;
		console.log(startNo+"__to__"+endNo);
		if(startNo - endNo < 0){
			$(ev.currentTarget).parent().find("tr:eq("+startNo+")").insertAfter($(ev.currentTarget).parent().find("tr:eq("+endNo+")"));
		}else{
			$(ev.currentTarget).parent().find("tr:eq("+startNo+")").insertBefore($(ev.currentTarget).parent().find("tr:eq("+endNo+")"));
		}
		$(ev.currentTarget).parent().find("tr:gt(0)").each(function(index, element){
			$(element).find(".dropdown-toggle").text(parseInt(index+1));
		});
		aggregate.moveTo(startNo-1, endNo-1);
	}

	$(schedule).find("td").on("dragstart", function(event){
		drag(event.originalEvent);
	});

	$(schedule).on("drop", function(event){
		drop(event.originalEvent);
	});

	$(schedule).on("dragover", function(event){
		allowDrop(event.originalEvent);
	});
}
// CoCo.test = function(){
// 	createSchedule();
// 	alert("hello coco");	
// }

try{
var fr = new FileReader();
}catch(e){
	console.debug("explorer don't surport :" );
	console.error("FileReader");
}

/** * 对Date的扩展，将 Date 转化为指定格式的String * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
    可以用 1-2 个占位符 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * (new
    Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04  
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern=function(fmt) {
	var o = {
	"M+" : this.getMonth()+1, //月份
	"d+" : this.getDate(), //日
	"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
	"H+" : this.getHours(), //小时
	"m+" : this.getMinutes(), //分
	"s+" : this.getSeconds(), //秒
	"q+" : Math.floor((this.getMonth()+3)/3), //季度
	};
	var week = {
		"0" : "\u65e5",
		"1" : "\u4e00",
		"2" : "\u4e8c",
		"3" : "\u4e09",
		"4" : "\u56db",
		"5" : "\u4e94",
		"6" : "\u516d"
	};
	if(/(y+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	if(/(E+)/.test(fmt)){
		fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);
	}
	for(var k in o){
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		}
	}
	return fmt; 
}

Date.prototype.getFmtMonth = function(){
	return (this.getMonth()< 9 ? "0" : "") + (this.getMonth() + 1);
}

Date.prototype.getFmtDate = function(){
	return (this.getDate()< 10 ? "0" : "") + this.getDate();
}

var fmtDate = function(date){
	return date.getFullYear()+"-"+date.getFmtMonth()+"-" + date.getFmtDate(); //exa: "2017-10-01"
}


// 迭代器实现对象，示意的是聚合对象为数组的迭代器
// 不同聚合对象相应的迭代器实现是不一样的
// @param {Array} aggregate [聚合对象]
var Iterator = function(aggregate){
	this.aggregate = aggregate;
	// 当前索引位置
	this.index = -1;
};
Iterator.prototype = {
	next: function(){
		return this.aggregate.get(++this.index);
	},
	isDone: function(){
		return this.index === this.aggregate.size() - 1;
	},
	currentItem: function(){
		return this.aggregate.get(this.index);
	}
};

var Aggregate = function(ss){
	this.ss = ss;
};
Aggregate.prototype = {
	createIterator: function(){
		return new Iterator(this);
	},
	get: function(index){
		var retObj = null;
		if(index < this.ss.length) {
			retObj = this.ss[index];
		}
		return retObj;
	},
	moveTo : function(index1, index2){
		var s = this.ss[index1];
		this.ss[index1] = this.ss[index2];
		this.ss[index2] = s;
	},
	size: function(){
		return this.ss.length;
	}
};

// var adw = new CoCo.Plan();
// var avl = JSON.stringify(adw);
// var bbc = JSON.parse(avl);
// bbc.prototype = new CoCo.Schedule();
// bbc.prototype.constructor = CoCo.Plan();
// console.error(adw instanceof CoCo.Schedule);