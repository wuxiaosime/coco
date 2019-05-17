//加载计划列表子页面-----
initPlanPanel = function(){$(".planPanel").load("page/plan_table.html", [], function(){
	console.log("load planPanel");
	// 点击保存
	$("#save-plan").on("click", function(event){
		// saveSchedule("#scheduleList", "schedules");
		var plan_list = $("#plan_tables .plan-table .planList");
		plan_list.each(function(index, plan){
			console.log(plan.id);
			savePlans("#"+plan.id, plan.id);
		});			
		// initSchedulesList();
		// initDateBoard();
	});
	// 点击新增
	$(".planPanel .add").on("click", function(event){
		console.log("add new plan");
		var newPlan = createPlan();
		// 点击清除
		newPlan.find(".btn-del-schedule").on("click", function(event){
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
		$(this).parents("table").append(newPlan);
		CoCo.dragScheduleBind(newPlan, new Aggregate(currentPlan));
	});
	
	createNewPlanList = function(){
		console.log("add new plan table");
		var newPlanTable = $(".plan-table-template").clone(true);
		newPlanTable.removeClass("plan-table-template");
		newPlanTable.addClass("plan-table");
		newPlanTable.find(".planList").attr("id", "plan_new");
		newPlanTable.find(".planList").data("plan-name", "手术");
		$("#plan_tables .swiper-wrapper").append(newPlanTable);
		mySwiper.update();
	} 
	
	deletePlanList = function(planid){
		coco.deletePlanList(planid);	
		mySwiper.removeSlide(mySwiper.realIndex);
	}
	
	// 点击新增计划
	$("#newPlanList").on("click", function(event){
		createNewPlanList();
	});
	
	// 点击删除计划
	$("#deletePlanList").on("click", function(event){
		var plan_id = $(mySwiper.slides[mySwiper.realIndex]).find(".planList").attr("id");
		deletePlanList(plan_id == "plan_new"? "plan_new" : plan_id.substring(5));
	});
	// $(".plan-table:eq(0) table").attr("id", nextId);
	// $.each(dayPlans, function(index, plans){
	// console.log("create new table : "+index);
	// var newPlanTable =
	// $("#"+nextId).parents(".plan-table").clone(true);
	// newPlanTable.find("table").attr("id", index);
	// $("#plan_tables .swiper-wrapper").append(newPlanTable);
	// initPlansList(index);
	// planNo++;
	// });
	// if(nextId.substring(nextId.indexOf("_")+1, nextId.length) > 1){
	// var templateTable = $("#"+nextId).parents(".plan-table");
	// $("#"+nextId).parents(".plan-table").detach();
	// $("#plan_tables .swiper-wrapper").append(templateTable);
	// }
	// var newPlanTable = $(".plan-table:eq(0)").clone(true);
	// newPlanTable[0].id = "plan_"+nextId;
	// $("#plan_tables .swiper-wrapper").append(newPlanTable);
	mySwiper = new Swiper('.swiper-container',{
		noSwiping : true,
		navigation:{
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev'
		},
		pagination: {
			el: '.swiper-pagination',
			type: 'fraction',
			renderCustom: function (currentClass, totalClass) {
			return + currentClass +' of ' + totalClass;
			}
		},
		on : {
			slideChangeTransitionEnd : function(){
				console.log($(this.slides[this.realIndex]).find(".planList").data("plan-name"));
				$("#plan_tables input[name='current-plan-name']").val($(this.slides[this.realIndex]).find(".planList").data("plan-name"));
				console.log("markers :" + Object.getOwnPropertyNames(markers).length);
				for (var p in markers) {
					if (markers.hasOwnProperty(p)){
						if(pmarkerReg.test(p)){
							map.removeOverlay(markers[p]);
							delete markers[p];
						}
					}
				}
				// this.slides[this.realIndex]
				// alert(this.realIndex);
			}
		}
	});
	initDateBoard();
	//计划单名称更改
	$("#plan_tables input[name='current-plan-name']").on("change", function(){
		console.log("current-plan-name change");
		$("#plan_tables .planlist-info .func").removeClass("hidden");
	});
	$("#plan_tables .planlist-info .func .confirm").on("click", function(){
		console.log("current-plan-name ok");
		if($(mySwiper.slides[mySwiper.realIndex]).find(".planList")){
			$(mySwiper.slides[mySwiper.realIndex]).find(".planList").data("plan-name", $("#plan_tables input[name='current-plan-name']").val());
			console.log($(mySwiper.slides[mySwiper.realIndex]).find(".planList").data("plan-name"));
			$("#plan_tables .planlist-info .func").addClass("hidden");
		}
	});
	$("#plan_tables .planlist-info .func .cancel").on("click", function(){
		console.log("current-plan-name cancel");
		if($(mySwiper.slides[mySwiper.realIndex]).find(".planList")){
			$("#plan_tables input[name='current-plan-name']").val($(mySwiper.slides[mySwiper.realIndex]).find(".planList").data("plan-name"));
			console.log($(mySwiper.slides[mySwiper.realIndex]).find(".planList").data("plan-name"));
			$("#plan_tables .planlist-info .func").addClass("hidden");
		}
	});
});
}//加载计划列表子页面*****