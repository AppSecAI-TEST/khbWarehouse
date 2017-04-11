    function share(appId,timestamp,nonceStr,signature,shareUrl){
      
      wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
       appId:appId,
        timestamp:timestamp, // 必填，生成签名的时间戳
        nonceStr: nonceStr, // 必填，生成签名的随机串
        signature: signature,// 必填，签名，见附录1
        jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
      });
      
    wx.ready(function(){
        //分享到朋友圈
        wx.onMenuShareTimeline({
          title: '加入懒猫金服享受信托理财稳健投资！', // 分享标题
          desc : '信托理财最高8.0%年化收益，不买你就亏了，好东西就要跟你分享，而且注册就送2390元投资大礼包',
          link: shareUrl, // 分享链接
          imgUrl: 'http://m.lanmao.com/lmweChat/static/images/shareImg.jpg', // 分享图标
          success: function () { 
              // 用户确认分享后执行的回调函数
            
          },
          cancel: function () { 
              // 用户取消分享后执行的回调函数
          }
        });
      //分享给朋友
        wx.onMenuShareAppMessage({
        title : '加入懒猫金服享受信托理财稳健投资！', // 分享标题
        desc : '信托理财最高8.0%年化收益，不买你就亏了，好东西就要跟你分享，而且注册就送2390元投资大礼包',
        link: shareUrl,
        imgUrl : 'http://m.lanmao.com/lmweChat/static/images/shareImg.jpg',
        type : '',
        dataUrl : '',
        success : function() {
          
        },
        cancel : function() {
        }
      });
      //分享至手机QQ
        wx.onMenuShareQQ({
          title: '加入懒猫金服享受信托理财稳健投资！', // 分享标题
          desc: '信托理财最高8.0%年化收益，不买你就亏了，好东西就要跟你分享，而且注册就送2390元投资大礼包', // 分享描述
          link: shareUrl, // 分享链接
          imgUrl: 'http://m.lanmao.com/lmweChat/static/images/shareImg.jpg', // 分享图标
          success: function () { 
             // 用户确认分享后执行的回调函数
          },
          cancel: function () { 
             // 用户取消分享后执行的回调函数
          }
      });
      //分享至QQ空间
        wx.onMenuShareWeibo({
          title: '加入懒猫金服享受信托理财稳健投资！', // 分享标题
          desc: '信托理财最高8.0%年化收益，不买你就亏了，好东西就要跟你分享，而且注册就送2390元投资大礼包', // 分享描述
          link: shareUrl, // 分享链接
          imgUrl: 'http://m.lanmao.com/lmweChat/static/images/shareImg.jpg', // 分享图标
          success: function () { 
             // 用户确认分享后执行的回调函数
          },
          cancel: function () { 
              // 用户取消分享后执行的回调函数
          }
      });
        
    });
    }
