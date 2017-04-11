    function shareFriend(appId,timestamp,nonceStr,signature,shareUrl,wxNickName){
      
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
          title: '这个游戏玩疯了！您的好友'+wxNickName+'邀请您一起抽大奖，赶快来注册吧，共同赢取幸运值抽大奖机会！', // 分享标题
          desc : '注册抽大奖，您的好友'+wxNickName+'邀请您一起抽大奖，点击注册马上开始。',
          link: shareUrl, // 分享链接
          imgUrl: 'http://m.lanmao.com/lmweChat/static/images/inviteFriendRaffle.jpg', // 分享图标
          success: function () { 
              // 用户确认分享后执行的回调函数
            
          },
          cancel: function () { 
              // 用户取消分享后执行的回调函数
          }
        });
      //分享给朋友
        wx.onMenuShareAppMessage({
        title : '懒猫金服', // 分享标题
        desc : '注册抽大奖，您的好友'+wxNickName+'邀请您一起抽大奖，点击注册马上开始。',
        link: shareUrl,
        imgUrl : 'http://m.lanmao.com/lmweChat/static/images/inviteFriendRaffle.jpg',
        type : '',
        dataUrl : '',
        success : function() {
          
        },
        cancel : function() {
        }
      });
      //分享至手机QQ
        wx.onMenuShareQQ({
          title: '懒猫金服', // 分享标题
          desc: '注册抽大奖，您的好友'+wxNickName+'邀请您一起抽大奖，点击注册马上开始。', // 分享描述
          link: shareUrl, // 分享链接
          imgUrl: 'http://m.lanmao.com/lmweChat/static/images/inviteFriendRaffle.jpg', // 分享图标
          success: function () { 
             // 用户确认分享后执行的回调函数
          },
          cancel: function () { 
             // 用户取消分享后执行的回调函数
          }
      });
      //分享至QQ空间
        wx.onMenuShareWeibo({
          title: '这个游戏玩疯了！您的好友'+wxNickName+'邀请您一起抽大奖，赶快来注册吧，共同赢取幸运值抽大奖机会！', // 分享标题
          desc: '注册抽大奖，您的好友'+wxNickName+'邀请您一起抽大奖，点击注册马上开始。', // 分享描述
          link: shareUrl, // 分享链接
          imgUrl: 'http://m.lanmao.com/lmweChat/static/images/inviteFriendRaffle.jpg', // 分享图标
          success: function () { 
             // 用户确认分享后执行的回调函数
          },
          cancel: function () { 
              // 用户取消分享后执行的回调函数
          }
      });
        
    });
    }
