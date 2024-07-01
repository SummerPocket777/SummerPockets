"use strict";const n=require("../../../../common/vendor.js"),l=require("./props.js"),t=require("./value.js"),r=require("../../libs/mixin/mpMixin.js"),a=require("../../libs/mixin/mixin.js"),m=require("../../libs/mixin/button.js"),d=require("../../libs/mixin/openType.js"),s=require("../../libs/function/index.js"),p={name:"up-text",mixins:[r.mpMixin,a.mixin,t.value,m.buttonMixin,d.openType,l.props],emits:["click"],computed:{valueStyle(){const e={textDecoration:this.decoration,fontWeight:this.bold?"bold":"normal",wordWrap:this.wordWrap,fontSize:s.addUnit(this.size)};return!this.type&&(e.color=this.color),this.isNvue&&this.lines&&(e.lines=this.lines),this.lineHeight&&(e.lineHeight=s.addUnit(this.lineHeight)),!this.isNvue&&this.block&&(e.display="block"),s.deepMerge(e,s.addStyle(this.customStyle))},isNvue(){return!1},isMp(){let e=!1;return e=!0,e}},data(){return{}},methods:{addStyle:s.addStyle,clickHandler(){this.call&&this.mode==="phone"&&n.index.makePhoneCall({phoneNumber:this.text}),this.$emit("click")}}};if(!Array){const e=n.resolveComponent("u-icon"),u=n.resolveComponent("u-link");(e+u)()}const h=()=>"../u-icon/u-icon.js",y=()=>"../u-link/u-link.js";Math||(h+y)();function v(e,u,c,S,g,o){return n.e({a:e.show},e.show?n.e({b:e.mode==="price"},e.mode==="price"?{c:n.n(e.type&&`u-text__value--${e.type}`),d:n.s(o.valueStyle)}:{},{e:e.prefixIcon},e.prefixIcon?{f:n.p({name:e.prefixIcon,customStyle:o.addStyle(e.iconStyle)})}:{},{g:e.mode==="link"},e.mode==="link"?{h:o.valueStyle.fontWeight,i:o.valueStyle.wordWrap,j:o.valueStyle.fontSize,k:n.n(e.type&&`u-text__value--${e.type}`),l:n.n(e.lines&&`u-line-${e.lines}`),m:n.p({text:e.value,href:e.href,underLine:!0})}:e.openType&&o.isMp?{o:n.t(e.value),p:n.s(o.valueStyle),q:e.index,r:e.openType,s:n.o((...i)=>e.onGetUserInfo&&e.onGetUserInfo(...i)),t:n.o((...i)=>e.onContact&&e.onContact(...i)),v:n.o((...i)=>e.onGetPhoneNumber&&e.onGetPhoneNumber(...i)),w:n.o((...i)=>e.onError&&e.onError(...i)),x:n.o((...i)=>e.onLaunchApp&&e.onLaunchApp(...i)),y:n.o((...i)=>e.onOpenSetting&&e.onOpenSetting(...i)),z:e.lang,A:e.sessionFrom,B:e.sendMessageTitle,C:e.sendMessagePath,D:e.sendMessageImg,E:e.showMessageCard,F:e.appParameter}:{G:n.t(e.value),H:n.s(o.valueStyle),I:n.n(e.type&&`u-text__value--${e.type}`),J:n.n(e.lines&&`u-line-${e.lines}`)},{n:e.openType&&o.isMp,K:e.suffixIcon},e.suffixIcon?{L:n.p({name:e.suffixIcon,customStyle:o.addStyle(e.iconStyle)})}:{},{M:n.n(e.customClass),N:e.margin,O:e.align==="left"?"flex-start":e.align==="center"?"center":"flex-end",P:n.o((...i)=>o.clickHandler&&o.clickHandler(...i))}):{})}const f=n._export_sfc(p,[["render",v],["__scopeId","data-v-0a574502"],["__file","C:/E/h/total/SummerPockets/uniapp-consumer-ui/uni_modules/uview-plus/components/u-text/u-text.vue"]]);wx.createComponent(f);
