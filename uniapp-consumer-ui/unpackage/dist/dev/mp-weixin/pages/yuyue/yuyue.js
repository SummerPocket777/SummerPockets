"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      myyuyueInfo: {
        myyuyueWhere: "胖哥俩",
        myyuyueWhen: "2024-07-01",
        myyuyueHow: "预约中"
      }
    };
  },
  methods: {
    gotomyyuyueInfo() {
      common_vendor.index.navigateTo({
        url: "/pages/yuyue/yuyueInfo"
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.myyuyueInfo.myyuyueWhere),
    b: common_vendor.t($data.myyuyueInfo.myyuyueWhen),
    c: common_vendor.t($data.myyuyueInfo.myyuyueHow),
    d: common_vendor.o((...args) => $options.gotomyyuyueInfo && $options.gotomyyuyueInfo(...args))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/lesson4/SummerPockets/uniapp-consumer-ui/pages/yuyue/yuyue.vue"]]);
wx.createPage(MiniProgramPage);
