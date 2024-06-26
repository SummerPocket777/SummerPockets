<template>
    <!-- 加入购物车动画 -->
    <view>
        <view class="good_box" v-show="!hide_good_box" :style="[{left:bus_x+'px', top:bus_y+'px', background:PrimaryColor}]"></view>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                PrimaryColor: '#fe461d', // 背景色
                hide_good_box: true,
                bus_x:0,
                bus_y:0,
            }
        },
        created(e) {
            // 默认掉落坐标
            this.busPos = {};
            this.busPos['x'] = uni.getSystemInfoSync().windowWidth - 140;
            this.busPos['y'] = uni.getSystemInfoSync().windowHeight + 100;
        },
        methods:{
            //点击商品触发的事件
            touchOnGoods: function(e,busPos) {
                console.log(e, '进入动画')
                if(busPos){//是否自定义掉落坐标
                    this.busPos = busPos
                }
                // 如果good_box正在运动
                if (!this.hide_good_box) return;
                this.finger = {};
                var topPoint = {};
                var flag = false; //false:倒序，true:正序
                this.finger["x"] = e.detail.clientX || e.touches[0].clientX; //点击的位置
                this.finger["y"] = e.detail.clientY || e.touches[0].clientY;
    
                if (this.finger['y'] < this.busPos['y']) {
                    topPoint['y'] = this.finger['y'] - 150;
                } else {
                    topPoint['y'] = this.busPos['y'] - 150;
                }
                topPoint['x'] = Math.abs(this.finger['x'] - this.busPos['x']) / 2;
    
                if (this.finger['x'] > this.busPos['x']) {
                    topPoint['x'] = (this.finger['x'] - this.busPos['x']) / 2 + this.busPos['x'];
                    console.log("0123")
                    this.linePos = this.bezier([this.busPos, topPoint, this.finger], 30);
                    flag = false
                } else { //
                    topPoint['x'] = (this.busPos['x'] - this.finger['x']) / 2 + this.finger['x'];
                    console.log("123")
                    this.linePos = this.bezier([this.finger, topPoint, this.busPos], 30);
                    flag = true
                }
                this.startAnimation(flag);
            },
            //开始动画
            startAnimation: function(flag) {
                uni.vibrateShort(); //震动方法
                var that = this,
                    bezier_points = that.linePos['bezier_points'],
                    index = bezier_points.length;
                console.log(bezier_points, 'bezier_points')
                this.hide_good_box = false,
                this.bus_x = that.finger['x']
                this.bus_y = that.finger['y']
                if (!flag) {
                    index = bezier_points.length;
                    this.timer = setInterval(function() {
                        index--;
                        that.bus_x = bezier_points[index]['x']
                        that.bus_y = bezier_points[index]['y']
                        if (index <= 1) {
                            clearInterval(that.timer);
                            that.hide_good_box = true
                        }
                    }, 22);
                } else {
                    index = 0;
                    this.timer = setInterval(function() {
                        index++;
                        that.bus_x = bezier_points[index]['x']
                        that.bus_y = bezier_points[index]['y']
                        if (index >= 28) {
                            clearInterval(that.timer);
                            that.hide_good_box = true
                        }
                    }, 22);
                }
            },
            bezier: function(points, times) {
                // 0、以3个控制点为例，点A,B,C,AB上设置点D,BC上设置点E,DE连线上设置点F,则最终的贝塞尔曲线是点F的坐标轨迹。
                // 1、计算相邻控制点间距。
                // 2、根据完成时间,计算每次执行时D在AB方向上移动的距离，E在BC方向上移动的距离。
                // 3、时间每递增100ms，则D,E在指定方向上发生位移, F在DE上的位移则可通过AD/AB = DF/DE得出。
                // 4、根据DE的正余弦值和DE的值计算出F的坐标。
                // 邻控制AB点间距
                var bezier_points = [];
                var points_D = [];
                var points_E = [];
                const DIST_AB = Math.sqrt(Math.pow(points[1]['x'] - points[0]['x'], 2) + Math.pow(points[1]['y'] - points[0]['y'], 2));
                // 邻控制BC点间距
                const DIST_BC = Math.sqrt(Math.pow(points[2]['x'] - points[1]['x'], 2) + Math.pow(points[2]['y'] - points[1]['y'], 2));
                // D每次在AB方向上移动的距离
                const EACH_MOVE_AD = DIST_AB / times;
                // E每次在BC方向上移动的距离 
                const EACH_MOVE_BE = DIST_BC / times;
                // 点AB的正切
                const TAN_AB = (points[1]['y'] - points[0]['y']) / (points[1]['x'] - points[0]['x']);
                // 点BC的正切
                const TAN_BC = (points[2]['y'] - points[1]['y']) / (points[2]['x'] - points[1]['x']);
                // 点AB的弧度值
                const RADIUS_AB = Math.atan(TAN_AB);
                // 点BC的弧度值
                const RADIUS_BC = Math.atan(TAN_BC);
                // 每次执行
                for (var i = 1; i <= times; i++) {
                    // AD的距离
                    var dist_AD = EACH_MOVE_AD * i;
                    // BE的距离
                    var dist_BE = EACH_MOVE_BE * i;
                    // D点的坐标
                    var point_D = {};
                    point_D['x'] = dist_AD * Math.cos(RADIUS_AB) + points[0]['x'];
                    point_D['y'] = dist_AD * Math.sin(RADIUS_AB) + points[0]['y'];
                    points_D.push(point_D);
                    // E点的坐标
                    var point_E = {};
                    point_E['x'] = dist_BE * Math.cos(RADIUS_BC) + points[1]['x'];
                    point_E['y'] = dist_BE * Math.sin(RADIUS_BC) + points[1]['y'];
                    points_E.push(point_E);
                    // 此时线段DE的正切值
                    var tan_DE = (point_E['y'] - point_D['y']) / (point_E['x'] - point_D['x']);
                    // tan_DE的弧度值
                    var radius_DE = Math.atan(tan_DE);
                    // 地市DE的间距
                    var dist_DE = Math.sqrt(Math.pow((point_E['x'] - point_D['x']), 2) + Math.pow((point_E['y'] - point_D['y']), 2));
                    // 此时DF的距离
                    var dist_DF = (dist_AD / DIST_AB) * dist_DE;
                    // 此时DF点的坐标
                    var point_F = {};
                    point_F['x'] = dist_DF * Math.cos(radius_DE) + point_D['x'];
                    point_F['y'] = dist_DF * Math.sin(radius_DE) + point_D['y'];
                    bezier_points.push(point_F);
                }
                return {
                    'bezier_points': bezier_points
                };
            },
        }
    }
</script>

<style lang="scss" scoped>
.good_box {
    width: 30rpx;
    height: 30rpx;
    position: fixed;
    border-radius: 50%;
    overflow: hidden;
    left: 50%;
    top: 50%;
    z-index: +99;
    border: 1px solid #fff;
    background: red;
}
</style>