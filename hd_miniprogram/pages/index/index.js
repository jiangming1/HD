// pages/mine.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  navigateToMiniprogram () {
    wx.login({
      success(res) {
        if (res.code) {
          console.log(res.code)
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
    wx.navigateToMiniProgram({
      appId: 'wx08ed3dc45df80a37',
      path: 'pages/index/index',
      extraData: {},
      envVersion: 'release',
      success(res) {
        // 打开成功
        wx.showToast({
          title: JSON.stringify(res),
          icon: '',
          image: '',
          duration: 0,
          mask: true,
          success: function(res) {},
          fail: function(res) {},
          complete: function(res) {},
        })
      }
    })
  }
})