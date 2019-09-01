//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    logs: [],
    text: ''
  },
  onLoad: function () {
    this.setData({
      logs: (wx.getStorageSync('logs') || []).map(log => {
        return util.formatTime(new Date(log))
      })
    })
  },
  scan() {
    wx.scanCode({
      success: (res) => {
        console.log("扫码结果");
        console.log(res);
        wx.showToast({
          title: res.result
        })
        this.setData({
          text: res.result
        })
      },
      fail: (res) => {
        console.log(res);
      }
    })
  }
})
