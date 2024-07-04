"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      loading: false,
      yuyueClass: "yuyue-box1",
      yuyueInfo: {
        peopleNumber: "",
        userName: "",
        userPhone: "",
        datetimesingle: ""
      },
      today: ""
    };
  },
  onLoad() {
    this.setToday();
  },
  methods: {
    setToday() {
      const now = /* @__PURE__ */ new Date();
      const year = now.getFullYear();
      const month = String(now.getMonth() + 1).padStart(2, "0");
      const date = String(now.getDate()).padStart(2, "0");
      const hours = String(now.getHours()).padStart(2, "0");
      const minutes = String(now.getMinutes()).padStart(2, "0");
      this.today = `${year}-${month}-${date} ${hours}:${minutes}`;
    },
    clike() {
      console.log(this.yuyueClass);
      this.loading = true;
      setTimeout(() => {
        this.loading = false;
        this.yuyueClass = "yuyue-box";
      }, 2e3);
    },
    handleChange(e) {
      console.log("日期" + e);
    },
    yuyueSure(e) {
      console.log(this.yuyueInfo);
      const selectedDateTime = new Date(this.yuyueInfo.datetimesingle).getTime();
      const currentDateTime = (/* @__PURE__ */ new Date()).getTime();
      const sevenDaysLaterDateTime = currentDateTime + 7 * 24 * 60 * 60 * 1e3;
      if (this.yuyueInfo.datetimesingle == "") {
        common_vendor.index.showToast({
          title: "请选择日期",
          icon: "error"
        });
      } else if (this.yuyueInfo.userPhone == "") {
        common_vendor.index.showToast({
          title: "请输入电话",
          icon: "error"
        });
      } else if (this.yuyueInfo.userName == "") {
        common_vendor.index.showToast({
          title: "请输入姓名",
          icon: "error"
        });
      } else if (this.yuyueInfo.userName.length > 10) {
        common_vendor.index.showToast({
          title: "请输入正确的昵称",
          icon: "error"
        });
      } else if (this.yuyueInfo.peopleNumber == "") {
        common_vendor.index.showToast({
          title: "请选择人数",
          icon: "error"
        });
      } else if (this.yuyueInfo.userPhone.length < 11) {
        common_vendor.index.showToast({
          title: "请输入正确的电话号码",
          icon: "none"
        });
      } else if (selectedDateTime < currentDateTime) {
        common_vendor.index.showToast({
          title: "选择有误",
          icon: "none"
        });
      } else if (selectedDateTime > sevenDaysLaterDateTime) {
        common_vendor.index.showToast({
          title: "请选择7天内的日期",
          icon: "none"
        });
      } else {
        common_vendor.index.request({
          url: "http://127.0.0.1:9999/book/add",
          method: "POST",
          data: {
            "bookNumber": this.yuyueInfo.peopleNumber,
            "bookDate": this.yuyueInfo.datetimesingle,
            "bookName": this.yuyueInfo.userName,
            "bookPhone": this.yuyueInfo.userPhone
          },
          success: (res) => {
            if (res.data.code == 2e4) {
              common_vendor.index.showToast({
                title: "预约成功",
                icon: "success"
              });
            } else {
              common_vendor.index.showToast({
                title: "网络请求失败",
                icon: "loading"
              });
            }
          },
          fail: (err) => {
            common_vendor.index.showToast({
              title: "网络请求失败",
              icon: "loading"
            });
          }
        });
      }
      this.yuyueInfo = {
        peopleNumber: "",
        userName: "",
        userPhone: "",
        datetimesingle: ""
      };
      this.yuyueClass = "yuyue-box1";
    },
    yuyueCancel() {
      this.yuyueInfo = {
        peopleNumber: "",
        userName: "",
        userPhone: "",
        datetimesingle: ""
      };
      this.yuyueClass = "yuyue-box1";
    }
  }
};
if (!Array) {
  const _easycom_uni_datetime_picker2 = common_vendor.resolveComponent("uni-datetime-picker");
  _easycom_uni_datetime_picker2();
}
const _easycom_uni_datetime_picker = () => "../../uni_modules/uni-datetime-picker/components/uni-datetime-picker/uni-datetime-picker.js";
if (!Math) {
  _easycom_uni_datetime_picker();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: $data.loading,
    b: common_vendor.o((...args) => $options.clike && $options.clike(...args)),
    c: $data.yuyueInfo.peopleNumber,
    d: common_vendor.o(($event) => $data.yuyueInfo.peopleNumber = $event.detail.value),
    e: common_vendor.o($options.handleChange),
    f: common_vendor.o(($event) => $data.yuyueInfo.datetimesingle = $event),
    g: common_vendor.p({
      type: "datetime",
      ["clear-icon"]: false,
      start: $data.today,
      modelValue: $data.yuyueInfo.datetimesingle
    }),
    h: $data.yuyueInfo.userName,
    i: common_vendor.o(($event) => $data.yuyueInfo.userName = $event.detail.value),
    j: $data.yuyueInfo.userPhone,
    k: common_vendor.o(($event) => $data.yuyueInfo.userPhone = $event.detail.value),
    l: common_vendor.o((...args) => $options.yuyueCancel && $options.yuyueCancel(...args)),
    m: common_vendor.o((...args) => $options.yuyueSure && $options.yuyueSure(...args)),
    n: common_vendor.n($data.yuyueClass)
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "C:/Users/cc129/IdeaProjects/SummerPockets/uniapp-consumer-ui/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
