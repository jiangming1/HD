//index.js
//获取应用实例
const app = getApp()
const request = require('../../utils/request.js')

Page({
  data: {
    hdLimitData: [],
    defaultImg: 'https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1653393584,2357658116&fm=173&app=49&f=JPEG?w=640&h=360&s=53947D8DF0B5698E958D62E103007097',
    videoList: [],
    defaultVideoUrl: 'https://tutorialehtml.com/assets_tutorials/media/Shaun-the-Sheep-The-Movie-Official-Trailer.mp4',
    wikiBool: true,
    videoBool: true
  },
  loadMore(){
    let _this = this;
    // wx.request({
    //   url: 'http://127.0.0.1:8080/web/encyclopedias/getAll.do',
    //   success(res) {
        
    //   }
    // })
    request._get('/encyclopedias/getAll.do').then( res => {
      console.log(res);
      _this.setData({
        hdLimitData: res,
        wikiBool: false
      })
    })
  },
  loadMoreVideo(){
    let _this = this;
    request._get('/video/getAll.do').then(res => {
      _this.setData({
        videoList: res,
        videoBool: false
      })
    })
  },
  onLoad(){
    let _this = this;
    request._get('/encyclopedias/getAhead.do').then(res => {
      _this.setData({
        hdLimitData: res
      })
    })

    request._get('/video/getAhead.do').then(res => {
      _this.setData({
        videoList: res
      })
    })
  }
})