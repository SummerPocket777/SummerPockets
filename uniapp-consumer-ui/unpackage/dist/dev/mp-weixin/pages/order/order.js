"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {};
  },
  methods: {
    toShop() {
      common_vendor.index.switchTab({
        url: "/pages/shop/shop"
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o((...args) => $options.toShop && $options.toShop(...args))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "C:/E/h/total/SummerPockets/uniapp-consumer-ui/pages/order/order.vue"]]);
wx.createPage(MiniProgramPage);
