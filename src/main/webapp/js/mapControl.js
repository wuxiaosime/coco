CoCo.MapControl = function(){
	this.planPois = [startPoint.point];
	this.polyline;
	this.isPlanning = false;
	this.planList = [];
	this.sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
				scale: 0.6,//图标缩放大小
				strokeColor:'#fff',//设置矢量图标的线填充颜色
				strokeWeight: '1',//设置线宽
				});
	this.icons = new BMap.IconSequence(this.sy, '10', '30');
	this.pickPoint = function(point, plan){
		console.log("pick point");
		var isContain = false;
		this.planPois.forEach(function(ele){
			if(ele.equals(point)){
				isContain = true;
				return false;
			}
		});
		if(isContain)
			return false;
		this.planPois.push(point);
		this.planList.push(plan);
		this.drawPlanRoute(this.planPois);
	}	
	this.drawPlanRoute = function(planPois){
		console.log("drawPlanRoute");
		map.removeOverlay(this.polyline);
		this.polyline =new BMap.Polyline(planPois, {
			enableEditing: false,//是否启用线编辑，默认为false
			enableClicking: true,//是否响应点击事件，默认为true
			icons:[this.icons],
			strokeWeight:'8',//折线的宽度，以像素为单位
			strokeOpacity: 0.8,//折线的透明度，取值范围0 - 1
			strokeColor:"#18a45b" //折线颜色
		});
		map.addOverlay(this.polyline);          //增加折线
	}
	this.finishPlan = function(){
		console.log(this.planList);
		currentPlan = this.planList;
	}
	// 定义一个控件类,即function
	function SeachBarControl(){
		// 默认停靠位置和偏移量
		this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		this.defaultOffset = new BMap.Size(5, 5);
	}
	// 通过JavaScript的prototype属性继承于BMap.Control
	SeachBarControl.prototype = new BMap.Control();
	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	SeachBarControl.prototype.initialize = function(map){
		var div = $(".map-panel .search-bar").clone(true);
		// 创建一个DOM元素		
		$(".map-panel .search-bar").remove();
		// 绑定事件,点击一次放大两级
		div.onclick = function(e){
		map.setZoom(map.getZoom() + 2);
		}
		// 添加DOM元素到地图中
		map.getContainer().appendChild(div[0]);
		// 将DOM元素返回
		return div[0];
	}
	// 创建控件
	var seachBarCtrl = new SeachBarControl();
	// 添加到地图当中
	map.addControl(seachBarCtrl);

	// 定义一个控件类,即function
	function ConfigControl(){
		// 默认停靠位置和偏移量
		this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
		this.defaultOffset = new BMap.Size(5, 45);
	}
	// 通过JavaScript的prototype属性继承于BMap.Control
	ConfigControl.prototype = new BMap.Control();

	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
	// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
	ConfigControl.prototype.initialize = function(map){
		// 创建一个DOM元素
		var div = $(".map-control").clone();
		$(".map-control").remove();
		// 绑定事件,点击一次放大两级
		div.onclick = function(e){
		map.setZoom(map.getZoom() + 2);
		}
		// 添加DOM元素到地图中
		map.getContainer().appendChild(div[0]);
		// 将DOM元素返回
		return div[0];
	}
	// 创建控件
	var confiCtrl = new ConfigControl();
	// 添加到地图当中
	map.addControl(confiCtrl);
	$("#map-config").on("mouseenter", function(event){
		event.originalEvent.stopPropagation();
		console.log("over");
		$(".map-control .panel-body").removeClass("hidden");
	});

	$(".map-control").on("mouseleave", function(event){
		event.originalEvent.stopPropagation();
		console.log("out");
		$(".map-control .panel-body").addClass("hidden");
	});
}