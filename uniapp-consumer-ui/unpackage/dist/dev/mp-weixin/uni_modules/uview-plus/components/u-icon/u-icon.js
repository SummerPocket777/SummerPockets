"use strict";const o=require("./icons.js"),u=require("./props.js"),d=require("../../libs/mixin/mpMixin.js"),r=require("../../libs/mixin/mixin.js"),n=require("../../libs/function/index.js"),t=require("../../libs/config/config.js"),s=require("../../../../common/vendor.js"),c={name:"u-icon",data(){return{}},emits:["click"],mixins:[d.mpMixin,r.mixin,u.props],computed:{uClasses(){let i=[];return i.push(this.customPrefix+"-"+this.name),this.customPrefix=="uicon"?i.push("u-iconfont"):i.push(this.customPrefix),this.color&&t.config.type.includes(this.color)&&i.push("u-icon__icon--"+this.color),i},iconStyle(){let i={};return i={fontSize:n.addUnit(this.size),lineHeight:n.addUnit(this.size),fontWeight:this.bold?"bold":"normal",top:n.addUnit(this.top)},this.color&&!t.config.type.includes(this.color)&&(i.color=this.color),i},isImg(){return this.name.indexOf("/")!==-1},imgStyle(){let i={};return i.width=this.width?n.addUnit(this.width):n.addUnit(this.size),i.height=this.height?n.addUnit(this.height):n.addUnit(this.size),i},icon(){return this.customPrefix!=="uicon"?"":o.icons["uicon-"+this.name]||this.name}},methods:{addStyle:n.addStyle,addUnit:n.addUnit,clickHandler(i){this.$emit("click",this.index),this.stop&&this.preventEvent(i)}}};function a(i,h,_,f,p,e){return s.e({a:e.isImg},e.isImg?{b:i.name,c:i.imgMode,d:s.s(e.imgStyle),e:s.s(e.addStyle(i.customStyle))}:{f:s.t(e.icon),g:s.n(e.uClasses),h:s.s(e.iconStyle),i:s.s(e.addStyle(i.customStyle)),j:i.hoverClass},{k:i.label!==""},i.label!==""?{l:s.t(i.label),m:i.labelColor,n:e.addUnit(i.labelSize),o:i.labelPos=="right"?e.addUnit(i.space):0,p:i.labelPos=="bottom"?e.addUnit(i.space):0,q:i.labelPos=="left"?e.addUnit(i.space):0,r:i.labelPos=="top"?e.addUnit(i.space):0}:{},{s:s.o((...l)=>e.clickHandler&&e.clickHandler(...l)),t:s.n("u-icon--"+i.labelPos)})}const m=s._export_sfc(c,[["render",a],["__scopeId","data-v-ac70166d"],["__file","C:/E/h/total/SummerPockets/uniapp-consumer-ui/uni_modules/uview-plus/components/u-icon/u-icon.vue"]]);wx.createComponent(m);
