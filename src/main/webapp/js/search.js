CoCo.SearchAddressPoi = function (searchAggregate, {map : map, listSelector : listSelector, savePolicy : savePolicy}) {
	this.searchIterator = searchAggregate.createIterator();
	this.searchInterval;
	this.startPoint = startPoint.point;
	this.search = function(){
		var that = this;
		// //根据终点地名查询终点坐标
		// var startSearchOptions = {
		// 	onSearchComplete: function(results){
		// 		// 判断状态是否正确
		// 		if (localStart.getStatus() == BMAP_STATUS_SUCCESS){
		// 			that.startPoint = results.getPoi(0).point;
		// 		}
		// 	}
		// };
		// var localStart = new BMap.LocalSearch(map, startSearchOptions);
		// localStart.search("四川省成都市二环路西一段116号浣花小区12幢");

		this.calcTransport = new BMap.DrivingRoute(map, {
			onSearchComplete : function (results){
				if (that.calcTransport.getStatus() != BMAP_STATUS_SUCCESS){
					console.info("未计算出路程的地址 : " + that.searchIterator.currentItem().title + "local.getStatus() = " + that.calcTransport.getStatus());
				}else{
					var plan = results.getPlan(0);
					var start = results.getStart();
					var end = results.getEnd();
					that.startPoint = start.point;
					endPoint = end.point;
					console.debug("终点坐标:"+endPoint.lng+"  "+endPoint.lat);
					//获取距离
					$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") input[name='distance']").val(plan.getDistance(true));
					//获取时间
					$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") input[name='duration']").val(plan.getDuration(true));
					$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") .location").removeClass("btn-warning");
					$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") .location").addClass("btn-success");
					var currentItem = that.searchIterator.currentItem();
					currentItem.distance = plan.getDistance(true);
					currentItem.duration = plan.getDuration(true);					
				}
				that.calcTransport.searchStatus = true;
			}
		});
		this.searchRoute = function(){
			if(!that.calcTransport.searchStatus)
				return;
			else
				that.calcTransport.searchStatus = false;
			if(that.searchIterator.isDone()){
				console.info("list searchRoute complete");
				/*if(savePolicy === PLAN_LIST){
					allPlans[currentDayFmt] = currentPlan; 
					localStorage["plans"] = JSON.stringify(allPlans);
				}*/
				window.clearInterval(that.searchInterval);
				that.searchIterator.index = -1;
				if ("searchPlan" in animationMap){
					animationMap["searchPlan"].stop();
				}
			}else{
				var previousSchedule = that.searchIterator.currentItem();
				var schedule = that.searchIterator.next();
				if(schedule && !schedule.distance && (schedule.endPointLng || ( previousSchedule && previousSchedule.endPointLng))){
					that.calcTransport.search(that.searchIterator.index < 1
						?that.startPoint : new BMap.Point(previousSchedule.endPointLng, previousSchedule.endPointLat)
						,new BMap.Point(schedule.endPointLng, schedule.endPointLat));
				}else{
					that.calcTransport.searchStatus = true;
				}
			}
		}
		//更新planlist
		var updatePlanList = function(address, point){
			importDate = fmtDate($("#importModal .date").datepicker("getDate"));
			var currentItem = that.searchIterator.currentItem();
			currentItem.endPointLng= point.lng;
			currentItem.endPointLat = point.lat;
			//保存终点坐标
			$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") input[name='endPoint-lng']").val(point.lng);
			$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") input[name='endPoint-lat']").val(point.lat);
			$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") .location").removeClass("btn-danger");
			$("#"+listSelector+"  tr:eq("+parseInt(that.searchIterator.index+1)+") .location").addClass("btn-warning");
			//console.error(currentItem instanceof CoCo.Plan);
			if(currentItem.className == "CoCo.Schedule"){
				var result = createMarker(null, currentItem, SCHEDULE_LIST);
				if(result)
					markers["s"+currentItem.no] = result;
			}
			else if(currentItem.className == "CoCo.Plan"){
				var result = createMarker(null, currentItem, PLAN_LIST);
				if(result)
					markers["p"+currentItem.no] = result;
			}
			else{
				console.error("incorrect className name" + currentItem.className);
			}
		}
		//根据终点地名查询终点坐标
		this.local = new BMap.LocalSearch(map, {
			onSearchComplete: function(results){
				console.debug(that);
				// 判断状态是否正确
				if (that.local.getStatus() == BMAP_STATUS_SUCCESS){
					//searchResPoiList.push(results.getPoi(0));
					console.info(that.searchIterator.index);
					console.info(results.getPoi(0).address + results.getPoi(0).title + results.getPoi(0));
					updatePlanList(results.getPoi(0).address + results.getPoi(0).title, results.getPoi(0).point);
					//calcTransport.search(startPoint, results.getPoi(0).point);
					//searchAddressPoi();
				}else if(that.local.getStatus() == BMAP_STATUS_UNKNOWN_LOCATION){
					console.info("未查询出的地址 : " + that.searchIterator.currentItem().title + "local.getStatus() = " + that.local.getStatus());
				}
				that.local.searchStatus = true;
			}
		});
		this.local.searchStatus = true;
		this.searchInterval = self.setInterval(function(){
			if(!that.local.searchStatus)
				return;
			else
				that.local.searchStatus = false;
			if(that.searchIterator.isDone()){
				console.info("list search complete");
				/*if(savePolicy === PLAN_LIST){
					allPlans[currentDayFmt] = currentPlan; 
					localStorage["plans"] = JSON.stringify(allPlans);
				}*/
				window.clearInterval(that.searchInterval);
				that.searchIterator.index = -1;
				that.calcTransport.searchStatus = true;
				that.searchInterval = self.setInterval(that.searchRoute, 50);
			}else{
				var schedule = that.searchIterator.next();
				if(schedule && schedule.address && coco.isEmpty(schedule.endPointLat)){
					console.info("search address:"+schedule.address);
					var subUnitIndex = schedule.address.search(/(\d{1,2}|[一二三四五六七八九十]{1,3})单元/);
					if(subUnitIndex > -1)
						schedule.address = schedule.address.substring(0, subUnitIndex);
					var subAddress = schedule.address.match(/.+/);
					console.info("search access sub address:"+ (subAddress == null? "none" : subAddress[0]));
					console.info("search access unit address:"+ (subUnitIndex == null? "none" : subUnitIndex));
					that.local.search(schedule.address);
				}else{
					that.local.searchStatus = true;
				}
			}
		},100);
	}
}