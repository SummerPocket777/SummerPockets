<template>
    <div id="container"></div>
  </template>
  <script>
  import AMapLoader from "@amap/amap-jsapi-loader";
  
  export default {
    name: "map-view",
  
    mounted() {
      this.initAMap();
    },
    unmounted() {
      this.map?.destroy();
    },
    data(){
      return{
      
      }
    },
    methods: {
      
      initAMap() {
        var _this = this
        window._AMapSecurityConfig = {
          securityJsCode: "8c1ca2f2239bb16b60114d8b4458c629",
        };
        AMapLoader.load({
          key: "67ec8f31660efface2be65ff016daaa4", // 申请好的Web端开发者Key，首次调用 load 时必填
          version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
          plugins: ["AMap.Scale",'AMap.Geolocation','AMap.Geocoder','AMap.ToolBar'], //需要使用的的插件列表，如比例尺'AMap.Scale'，支持添加多个如：['...','...']
          AMapUI: {// 加载ui组件库
              version: '1.1',
              plugins: ['misc/PositionPicker']//SimpleMarker设置自定义图标，PathSimplifier轨迹展示组件
          }
        }).then((AMap) => {
            var map = new AMap.Map("container", {
              // 设置地图容器id
              viewMode: "3D", // 是否为3D地图模式
              zoom: 18, // 初始化地图级别
            //   center: [116.397428, 39.90923], // 初始化地图中心点位置
            });
            
            AMap.plugin('AMap.Geolocation', function () {   // 获取定位信息
                    var geolocation = new AMap.Geolocation({
                        enableHighAccuracy: true,//是否使用高精度定位，默认:true
                        timeout: 10000,          //超过10秒后停止定位，默认：无穷大
                        maximumAge: 0,           //定位结果缓存0毫秒，默认：0
                        convert: true,           //自动偏移坐标，偏移后的坐标为高德坐标，默认：true
                        // showButton: true,        //显示定位按钮，默认：true
                        position: 'RB',    //定位按钮停靠位置，默认：'LB'，左下角
                        offset: [20,90], //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
                        showMarker: false,        //定位成功后在定位到的位置显示点标记，默认：true
                        showCircle: false,        //定位成功后用圆圈表示定位精度范围，默认：true
                        panToLocation: true,     //定位成功后将定位到的位置作为地图中心点，默认：true
                        zoomToAccuracy:true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
                        needAddress:true,
                        extensions:"all"
                    });

                    function onComplete(obj){
                    // console.log("complete: ",obj);
                    // console.log("地址: ",obj.formattedAddress)
                    // this.getAddress=obj
                    // console.log("address: ",this.getAddress)
                    // _this.$emit('sendAddress',obj)
                    
                    }

                    function onError(obj){
                    console.log("error: ",obj.message);
                    }       
                    map.addControl(geolocation);
                    // AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
                    // AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
                    geolocation.getCurrentPosition(function(status,result){
                    if(status=='complete'){
                        onComplete(result)
                    }else{
                        onError(result)
                    }
                });
            });

            AMapUI.loadUI(['misc/PositionPicker'], function(PositionPicker) {  // 异步加载组件
        
              var positionPicker = new PositionPicker({
                  mode: 'dragMap',  //拖拽地图
                  map: map       // 告知在那个地图实例工作
              });

              positionPicker.on('success', function(positionResult) {
                  // console.log("拖拽地图成功: ",positionResult)
                  _this.$emit('sendAddress',positionResult)
              });
              positionPicker.on('fail', function(positionResult) {
                  console.log("拖拽地图失败: ",positionResult)
              });

              positionPicker.start();
              map.panBy(0, 1);

              map.addControl(new AMap.ToolBar({
                  liteStyle: true
              }))

            })
            
          }).catch((e) => {
            console.log(e);
          });
      },
    },
  };
  </script>
  <style scoped>
  #container {
    width: 100%;
    height: 300px;
  }
  </style>
  