"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      myyuyueinfos: {
        peopleNumber: "6",
        yuyueTimer: "2024-06-29 12:00",
        yuyueName: "王先生",
        yueyuePhone: "12345678901",
        creatYuyueTime: "2024-06-25 12:00"
      }
    };
  },
  methods: {
    cancelYuyue() {
      common_vendor.index.showModal({
        title: "取消预约",
        content: "您确定要取消预约？",
        success: function(res) {
          if (res.confirm) {
            common_vendor.index.showToast({
              title: "您已成功取消",
              icon: "success"
            });
            setTimeout(() => {
              common_vendor.index.navigateTo({
                url: "/pages/yuyue/yuyue"
              });
            }, 500);
          } else if (res.cancel) {
            common_vendor.index.showToast({
              title: "取消本次操作",
              icon: "success"
            });
          }
        }
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.myyuyueinfos.peopleNumber),
    b: common_vendor.t($data.myyuyueinfos.yuyueTimer),
    c: common_vendor.t($data.myyuyueinfos.yuyueName),
    d: common_vendor.t($data.myyuyueinfos.yueyuePhone),
    e: common_vendor.t($data.myyuyueinfos.creatYuyueTime),
    f: common_vendor.o((...args) => $options.cancelYuyue && $options.cancelYuyue(...args))
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "C:/Users/cc129/IdeaProjects/SummerPockets/uniapp-consumer-ui/pages/yuyue/yuyueInfo.vue"]]);
wx.createPage(MiniProgramPage);
