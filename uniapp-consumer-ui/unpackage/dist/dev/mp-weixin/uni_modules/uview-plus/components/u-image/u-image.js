"use strict";const q=require("./props.js"),d=require("../../libs/mixin/mpMixin.js"),a=require("../../libs/mixin/mixin.js"),o=require("../../libs/function/index.js"),i=require("../../../../common/vendor.js");require("../../libs/vue.js");require("../../libs/config/props.js");require("../../libs/config/config.js");require("../../libs/config/props/actionSheet.js");require("../../libs/config/props/album.js");require("../../libs/config/props/alert.js");require("../../libs/config/props/avatar.js");require("../../libs/config/props/avatarGroup.js");require("../../libs/config/props/backtop.js");require("../../libs/config/props/badge.js");require("../../libs/config/props/button.js");require("../../libs/config/props/calendar.js");require("../../libs/config/props/carKeyboard.js");require("../../libs/config/props/cell.js");require("../../libs/config/props/cellGroup.js");require("../../libs/config/props/checkbox.js");require("../../libs/config/props/checkboxGroup.js");require("../../libs/config/props/circleProgress.js");require("../../libs/config/props/code.js");require("../../libs/config/props/codeInput.js");require("../../libs/config/props/col.js");require("../../libs/config/props/collapse.js");require("../../libs/config/props/collapseItem.js");require("../../libs/config/props/columnNotice.js");require("../../libs/config/props/countDown.js");require("../../libs/config/props/countTo.js");require("../../libs/config/props/datetimePicker.js");require("../../libs/config/props/divider.js");require("../../libs/config/props/empty.js");require("../../libs/config/props/form.js");require("../../libs/config/props/formItem.js");require("../../libs/config/props/gap.js");require("../../libs/config/props/grid.js");require("../../libs/config/props/gridItem.js");require("../../libs/config/props/icon.js");require("../../libs/config/props/image.js");require("../../libs/config/props/indexAnchor.js");require("../../libs/config/props/indexList.js");require("../../libs/config/props/input.js");require("../../libs/config/props/keyboard.js");require("../../libs/config/props/line.js");require("../../libs/config/props/lineProgress.js");require("../../libs/config/props/link.js");require("../../libs/config/props/list.js");require("../../libs/config/props/listItem.js");require("../../libs/config/props/loadingIcon.js");require("../../libs/config/props/loadingPage.js");require("../../libs/config/props/loadmore.js");require("../../libs/config/props/modal.js");require("../../libs/config/props/navbar.js");require("../../libs/config/color.js");require("../../libs/config/props/noNetwork.js");require("../../libs/config/props/noticeBar.js");require("../../libs/config/props/notify.js");require("../../libs/config/props/numberBox.js");require("../../libs/config/props/numberKeyboard.js");require("../../libs/config/props/overlay.js");require("../../libs/config/props/parse.js");require("../../libs/config/props/picker.js");require("../../libs/config/props/popup.js");require("../../libs/config/props/radio.js");require("../../libs/config/props/radioGroup.js");require("../../libs/config/props/rate.js");require("../../libs/config/props/readMore.js");require("../../libs/config/props/row.js");require("../../libs/config/props/rowNotice.js");require("../../libs/config/props/scrollList.js");require("../../libs/config/props/search.js");require("../../libs/config/props/section.js");require("../../libs/config/props/skeleton.js");require("../../libs/config/props/slider.js");require("../../libs/config/props/statusBar.js");require("../../libs/config/props/steps.js");require("../../libs/config/props/stepsItem.js");require("../../libs/config/props/sticky.js");require("../../libs/config/props/subsection.js");require("../../libs/config/props/swipeAction.js");require("../../libs/config/props/swipeActionItem.js");require("../../libs/config/props/swiper.js");require("../../libs/config/props/swipterIndicator.js");require("../../libs/config/props/switch.js");require("../../libs/config/props/tabbar.js");require("../../libs/config/props/tabbarItem.js");require("../../libs/config/props/tabs.js");require("../../libs/config/props/tag.js");require("../../libs/config/props/text.js");require("../../libs/config/props/textarea.js");require("../../libs/config/props/toast.js");require("../../libs/config/props/toolbar.js");require("../../libs/config/props/tooltip.js");require("../../libs/config/props/transition.js");require("../../libs/config/props/upload.js");require("../../libs/function/test.js");require("../../libs/util/route.js");require("../../libs/function/digit.js");const t={name:"u-image",mixins:[d.mpMixin,a.mixin,q.props],data(){return{isError:!1,loading:!0,opacity:1,durationTime:this.duration,backgroundStyle:{},show:!1}},watch:{src:{immediate:!0,handler(e){e?(this.isError=!1,this.loading=!0):this.isError=!0}}},computed:{wrapStyle(){let e={};return e.width=o.addUnit(this.width),e.height=o.addUnit(this.height),e.borderRadius=this.shape=="circle"?"10000px":o.addUnit(this.radius),e.overflow=this.radius>0?"hidden":"visible",o.deepMerge(e,o.addStyle(this.customStyle))}},mounted(){this.show=!0},emits:["click","error","load"],methods:{addUnit:o.addUnit,onClick(){this.$emit("click")},onErrorHandler(e){this.loading=!1,this.isError=!0,this.$emit("error",e)},onLoadHandler(e){this.loading=!1,this.isError=!1,this.$emit("load",e),this.removeBgColor()},removeBgColor(){this.backgroundStyle={backgroundColor:"transparent"}}}};if(!Array){const e=i.resolveComponent("u-icon"),s=i.resolveComponent("u-transition");(e+s)()}const h=()=>"../u-icon/u-icon.js",l=()=>"../u-transition/u-transition.js";Math||(h+l)();function m(e,s,g,w,u,r){return i.e({a:!u.isError},u.isError?{}:{b:e.src,c:e.mode,d:i.o((...n)=>r.onErrorHandler&&r.onErrorHandler(...n)),e:i.o((...n)=>r.onLoadHandler&&r.onLoadHandler(...n)),f:e.showMenuByLongpress,g:e.lazyLoad,h:e.shape=="circle"?"10000px":r.addUnit(e.radius),i:r.addUnit(e.width),j:r.addUnit(e.height)},{k:e.showLoading&&u.loading},e.showLoading&&u.loading?{l:i.p({name:e.loadingIcon,width:e.width,height:e.height}),m:e.shape=="circle"?"50%":r.addUnit(e.radius),n:this.bgColor,o:r.addUnit(e.width),p:r.addUnit(e.height)}:{},{q:e.showError&&u.isError&&!u.loading},e.showError&&u.isError&&!u.loading?{r:i.p({name:e.errorIcon,width:e.width,height:e.height}),s:e.shape=="circle"?"50%":r.addUnit(e.radius),t:r.addUnit(e.width),v:r.addUnit(e.height)}:{},{w:i.o((...n)=>r.onClick&&r.onClick(...n)),x:i.s(r.wrapStyle),y:i.s(u.backgroundStyle),z:i.p({mode:"fade",show:u.show,duration:e.fade?1e3:0})})}const c=i._export_sfc(t,[["render",m],["__scopeId","data-v-abebd402"],["__file","C:/Users/57520/Desktop/SummerPockets/uniapp-consumer-ui/uni_modules/uview-plus/components/u-image/u-image.vue"]]);wx.createComponent(c);
