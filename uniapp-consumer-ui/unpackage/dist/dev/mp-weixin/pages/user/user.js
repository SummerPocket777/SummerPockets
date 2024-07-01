"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      userName: "用户名称"
    };
  },
  onLoad() {
  },
  methods: {
    gotomyyuyue() {
      common_vendor.index.navigateTo({
        url: "../yuyue/yuyue"
      });
    }
  }
};
if (!Array) {
  const _easycom_up_icon2 = common_vendor.resolveComponent("up-icon");
  _easycom_up_icon2();
}
const _easycom_up_icon = () => "../../uni_modules/uview-plus/components/u-icon/u-icon.js";
if (!Math) {
  _easycom_up_icon();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.userName),
    b: common_vendor.p({
      name: "arrow-right"
    }),
    c: common_vendor.p({
      name: "arrow-right"
    }),
    d: common_vendor.p({
      name: "arrow-right"
    }),
    e: common_vendor.o((...args) => $options.gotomyyuyue && $options.gotomyyuyue(...args))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/lesson4/SummerPockets/uniapp-consumer-ui/pages/user/user.vue"]]);
wx.createPage(MiniProgramPage);
