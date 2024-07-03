"use strict";
Object.defineProperty(exports, Symbol.toStringTag, { value: "Module" });
const common_vendor = require("./common/vendor.js");
const uni_modules_uviewPlus_index = require("./uni_modules/uview-plus/index.js");
if (!Math) {
  "./pages/index/index.js";
  "./pages/order/order.js";
  "./pages/user/user.js";
  "./pages/shop/shop.js";
  "./pages/order/test.js";
  "./pages/user/coupons.js";
  "./pages/user/mycoupons.js";
  "./pages/user/redeem.js";
  "./pages/yuyue/yuyue.js";
  "./pages/yuyue/yuyueInfo.js";
}
const _sfc_main = {
  onLaunch: function() {
    console.log("App Launch");
  },
  onShow: function() {
    console.log("App Show");
  },
  onHide: function() {
    console.log("App Hide");
  }
};
const App = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "E:/lesson4/new/SummerPockets/uniapp-consumer-ui/App.vue"]]);
function createApp() {
  const app = common_vendor.createSSRApp(App);
  app.use(uni_modules_uviewPlus_index.uviewPlus);
  return {
    app
  };
}
createApp().app.mount("#app");
exports.createApp = createApp;
