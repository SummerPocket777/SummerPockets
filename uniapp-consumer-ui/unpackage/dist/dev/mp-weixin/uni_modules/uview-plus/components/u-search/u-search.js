"use strict";const r=require("../../../../common/vendor.js"),q=require("./props.js"),c=require("../../libs/mixin/mpMixin.js"),a=require("../../libs/mixin/mixin.js"),l=require("../../libs/function/index.js");require("../../libs/vue.js");require("../../libs/config/props.js");require("../../libs/config/config.js");require("../../libs/config/props/actionSheet.js");require("../../libs/config/props/album.js");require("../../libs/config/props/alert.js");require("../../libs/config/props/avatar.js");require("../../libs/config/props/avatarGroup.js");require("../../libs/config/props/backtop.js");require("../../libs/config/props/badge.js");require("../../libs/config/props/button.js");require("../../libs/config/props/calendar.js");require("../../libs/config/props/carKeyboard.js");require("../../libs/config/props/cell.js");require("../../libs/config/props/cellGroup.js");require("../../libs/config/props/checkbox.js");require("../../libs/config/props/checkboxGroup.js");require("../../libs/config/props/circleProgress.js");require("../../libs/config/props/code.js");require("../../libs/config/props/codeInput.js");require("../../libs/config/props/col.js");require("../../libs/config/props/collapse.js");require("../../libs/config/props/collapseItem.js");require("../../libs/config/props/columnNotice.js");require("../../libs/config/props/countDown.js");require("../../libs/config/props/countTo.js");require("../../libs/config/props/datetimePicker.js");require("../../libs/config/props/divider.js");require("../../libs/config/props/empty.js");require("../../libs/config/props/form.js");require("../../libs/config/props/formItem.js");require("../../libs/config/props/gap.js");require("../../libs/config/props/grid.js");require("../../libs/config/props/gridItem.js");require("../../libs/config/props/icon.js");require("../../libs/config/props/image.js");require("../../libs/config/props/indexAnchor.js");require("../../libs/config/props/indexList.js");require("../../libs/config/props/input.js");require("../../libs/config/props/keyboard.js");require("../../libs/config/props/line.js");require("../../libs/config/props/lineProgress.js");require("../../libs/config/props/link.js");require("../../libs/config/props/list.js");require("../../libs/config/props/listItem.js");require("../../libs/config/props/loadingIcon.js");require("../../libs/config/props/loadingPage.js");require("../../libs/config/props/loadmore.js");require("../../libs/config/props/modal.js");require("../../libs/config/props/navbar.js");require("../../libs/config/color.js");require("../../libs/config/props/noNetwork.js");require("../../libs/config/props/noticeBar.js");require("../../libs/config/props/notify.js");require("../../libs/config/props/numberBox.js");require("../../libs/config/props/numberKeyboard.js");require("../../libs/config/props/overlay.js");require("../../libs/config/props/parse.js");require("../../libs/config/props/picker.js");require("../../libs/config/props/popup.js");require("../../libs/config/props/radio.js");require("../../libs/config/props/radioGroup.js");require("../../libs/config/props/rate.js");require("../../libs/config/props/readMore.js");require("../../libs/config/props/row.js");require("../../libs/config/props/rowNotice.js");require("../../libs/config/props/scrollList.js");require("../../libs/config/props/search.js");require("../../libs/config/props/section.js");require("../../libs/config/props/skeleton.js");require("../../libs/config/props/slider.js");require("../../libs/config/props/statusBar.js");require("../../libs/config/props/steps.js");require("../../libs/config/props/stepsItem.js");require("../../libs/config/props/sticky.js");require("../../libs/config/props/subsection.js");require("../../libs/config/props/swipeAction.js");require("../../libs/config/props/swipeActionItem.js");require("../../libs/config/props/swiper.js");require("../../libs/config/props/swipterIndicator.js");require("../../libs/config/props/switch.js");require("../../libs/config/props/tabbar.js");require("../../libs/config/props/tabbarItem.js");require("../../libs/config/props/tabs.js");require("../../libs/config/props/tag.js");require("../../libs/config/props/text.js");require("../../libs/config/props/textarea.js");require("../../libs/config/props/toast.js");require("../../libs/config/props/toolbar.js");require("../../libs/config/props/tooltip.js");require("../../libs/config/props/transition.js");require("../../libs/config/props/upload.js");require("../../libs/function/test.js");require("../../libs/util/route.js");require("../../libs/function/digit.js");const n={name:"u-search",mixins:[c.mpMixin,a.mixin,q.props],data(){return{keyword:"",showClear:!1,show:!1,focused:this.focus}},watch:{keyword(e){this.$emit("update:modelValue",e),this.$emit("change",e)},modelValue:{immediate:!0,handler(e){this.keyword=e}}},computed:{showActionBtn(){return!this.animation&&this.showAction}},emits:["clear","search","custom","focus","blur","click","clickIcon","update:modelValue","change"],methods:{addStyle:l.addStyle,addUnit:l.addUnit,inputChange(e){this.keyword=e.detail.value},clear(){this.keyword="",this.$nextTick(()=>{this.$emit("clear")})},search(e){this.$emit("search",e.detail.value);try{r.index.hideKeyboard()}catch{}},custom(){this.$emit("custom",this.keyword);try{r.index.hideKeyboard()}catch{}},getFocus(){this.focused=!0,this.animation&&this.showAction&&(this.show=!0),this.$emit("focus",this.keyword)},blur(){setTimeout(()=>{this.focused=!1},100),this.show=!1,this.$emit("blur",this.keyword)},clickHandler(){this.disabled&&this.$emit("click")},clickIcon(e){this.$emit("clickIcon",this.keyword);try{r.index.hideKeyboard()}catch{}}}};Array||r.resolveComponent("u-icon")();const t=()=>"../u-icon/u-icon.js";Math||t();function h(e,s,m,f,o,i){return r.e({a:e.$slots.label||e.label!==null},e.$slots.label||e.label!==null?{b:r.t(e.label)}:{},{c:r.o(i.clickIcon),d:r.p({size:e.searchIconSize,name:e.searchIcon,color:e.searchIconColor?e.searchIconColor:e.color}),e:r.o((...u)=>i.blur&&i.blur(...u)),f:o.keyword,g:r.o((...u)=>i.search&&i.search(...u)),h:r.o((...u)=>i.inputChange&&i.inputChange(...u)),i:e.disabled,j:r.o((...u)=>i.getFocus&&i.getFocus(...u)),k:e.focus,l:e.maxlength,m:e.adjustPosition,n:e.autoBlur,o:e.placeholder,p:`color: ${e.placeholderColor}`,q:r.s({textAlign:e.inputAlign,color:e.color,backgroundColor:e.bgColor,height:i.addUnit(e.height)}),r:r.s(e.inputStyle),s:o.keyword&&e.clearabled&&o.focused},o.keyword&&e.clearabled&&o.focused?{t:r.p({name:"close",size:"11",color:"#ffffff",customStyle:"line-height: 12px"}),v:r.o((...u)=>i.clear&&i.clear(...u))}:{},{w:e.bgColor,x:e.shape=="round"?"100px":"4px",y:e.borderColor,z:r.t(e.actionText),A:r.s(e.actionStyle),B:r.n((i.showActionBtn||o.show)&&"u-search__action--active"),C:r.o((...u)=>i.custom&&i.custom(...u)),D:r.o((...u)=>i.clickHandler&&i.clickHandler(...u)),E:r.s({margin:e.margin}),F:r.s(i.addStyle(e.customStyle))})}const d=r._export_sfc(n,[["render",h],["__scopeId","data-v-e082a34a"],["__file","C:/Users/57520/Desktop/SummerPockets/uniapp-consumer-ui/uni_modules/uview-plus/components/u-search/u-search.vue"]]);wx.createComponent(d);
