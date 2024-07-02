"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {};
  }
};
if (!Array) {
  const _easycom_u_image2 = common_vendor.resolveComponent("u-image");
  const _easycom_u_text2 = common_vendor.resolveComponent("u-text");
  const _easycom_up_search2 = common_vendor.resolveComponent("up-search");
  const _component_scrolle_view = common_vendor.resolveComponent("scrolle-view");
  (_easycom_u_image2 + _easycom_u_text2 + _easycom_up_search2 + _component_scrolle_view)();
}
const _easycom_u_image = () => "../../uni_modules/uview-plus/components/u-image/u-image.js";
const _easycom_u_text = () => "../../uni_modules/uview-plus/components/u-text/u-text.js";
const _easycom_up_search = () => "../../uni_modules/uview-plus/components/u-search/u-search.js";
if (!Math) {
  (_easycom_u_image + _easycom_u_text + _easycom_up_search)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.p({
      width: "150rpx",
      height: "150rpx",
      shape: "square",
      radius: "5px"
    }),
    b: common_vendor.p({
      text: "桌名+桌号",
      bold: true,
      size: "17px"
    }),
    c: common_vendor.p({
      text: "用餐人数: "
    }),
    d: common_vendor.p({
      placeholder: "请输入菜品",
      ["input-align"]: "center",
      ["action-text"]: ""
    }),
    e: common_vendor.p({
      ["scroll-y"]: "true"
    }),
    f: common_vendor.p({
      width: "150rpx",
      height: "150rpx",
      shape: "square",
      radius: "5px"
    }),
    g: common_vendor.p({
      text: "榴莲面包",
      bold: true
    }),
    h: common_vendor.p({
      text: "暂无介绍",
      size: "13px"
    }),
    i: common_vendor.p({
      text: "销量: ",
      size: "13px"
    }),
    j: common_vendor.p({
      text: "0",
      size: "13px"
    }),
    k: common_vendor.p({
      ["prefix-icon"]: "",
      mode: "price",
      text: "29.80",
      color: "red"
    })
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/4thSummerPockets/uniapp-consumer-ui/pages/shop/shop.vue"]]);
wx.createPage(MiniProgramPage);
