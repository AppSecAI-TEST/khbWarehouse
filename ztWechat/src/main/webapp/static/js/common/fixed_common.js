function commonSwitch (data,firstColumn,productId) {
  getProductById(productId,firstColumn);
  switch (data.length) {
  case 2:
    str = "";
    str1 = '';
    str2 = '';
    data.forEach(function (itmes) {
          if (itmes.productId == productId) {
            str1 = "<li  class=\"on\" title=\""
                + itmes.productId
                + "\" onclick=\"mony(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
          } else {
            str2 = "<li title=\""
                + itmes.productId
                + "\"  onclick=\"mony(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
          }
        });
    str = str
        + str2
        + str1
        + str2
        + "</ul>"
        + "   <a href=\"javascript:void(0)\" onclick=\"monyMove(this)\" class=\"icon icon-arrow-l prev pa\"></a><a   onclick=\"monyMove(this)\" href=\"javascript:void(0)\" class=\"icon icon-arrow-r next pa\"></a>";
    break;
  case 3:
    var flag = false;
    var str1 = 0;
    var str2 = 0;
    var str3 = 0;
    data.forEach(function (itmes) {
          if (itmes.productId == productId) {
            str2 = "<li  class=\"on\" title=\""+ itmes.productId+"\" onclick=\"quay(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay

                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
          } else {

            if (flag) {
              str3 = "<li  title=\""+ itmes.productId+"\" onclick=\"quay(this,"
                  + itmes.productId
                  + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                  + itmes.termDay

                  + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                  + format_yearRate(itmes.yearRate)
                  + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
              return false;
            }
            str1 = "<li title=\""+ itmes.productId+"\" onclick=\"quay(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay

                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
            flag = true;
          }
        });
    str = ""
      + str1
      + str2
      + str3
      + "</ul>"
      + "   <a href=\"javascript:void(0)\" onclick=\"quayMove(this,1)\" class=\"icon icon-arrow-l prev pa\"></a><a   onclick=\"quayMove(this,2)\" href=\"javascript:void(0)\" class=\"icon icon-arrow-r next pa\"></a>";
    break;
  case 1:
    str = "<li ></li><li class=\"on\" title=\""
        + data[0].productId+"\""
        + " onclick=\"getInfo("
        + data[0].productId
        + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
        + data[0].termDay
        + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
        + format_yearRate(data[0].yearRate)
        + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li><li></li>";
    break;
  default:
    break;
  }
  return str;
}
//方案二
/*function commonSwitch (data) {
  var productId = $("#productId").val();
  getProductById(productId);
  switch (data.length) {
  case 2:
    str = "<ul id=\"show_day\">";
    str1 = '';
    str2 = '';
    data
        .forEach(function (itmes) {
          if (itmes.productId == productId) {
            str1 = "<li  class=\"on\" title=\""
                + itmes.productId
                + "\" onclick=\"mony(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
          } else {
            str2 = "<li title=\""
                + itmes.productId
                + "\"  onclick=\"mony(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay
                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
          }
        });
    str = str
        + str2
        + str1
        + str2
        + "</ul>"
        + "   <a href=\"javascript:void(0)\" onclick=\"monyMove()\" class=\"icon icon-arrow-l prev pa\"></a><a   onclick=\"monyMove()\" href=\"javascript:void(0)\" class=\"icon icon-arrow-r next pa\"></a>";
    break;
  case 3:
    var flag = false;
    var str1 = 0;
    var str2 = 0;
    var str3 = 0;
    data
        .forEach(function (itmes) {
          if (itmes.productId == productId) {
            str2 = "<li  class=\"on\" title=\""+ itmes.productId+"\" onclick=\"quay(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay

                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
          } else {

            if (flag) {
              str3 = "<li  title=\""+ itmes.productId+"\" onclick=\"quay(this,"
                  + itmes.productId
                  + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                  + itmes.termDay

                  + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                  + format_yearRate(itmes.yearRate)
                  + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
              return false;
            }
            str1 = "<li title=\""+ itmes.productId+"\" onclick=\"quay(this,"
                + itmes.productId
                + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
                + itmes.termDay

                + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
                + format_yearRate(itmes.yearRate)
                + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li>";
            flag = true;
          }
        });
    str = "<ul id=\"show_month\">"
      + str1
      + str2
      + str3
      + "</ul>"
      + "   <a href=\"javascript:void(0)\" onclick=\"quayMove(1)\" class=\"icon icon-arrow-l prev pa\"></a><a   onclick=\"quayMove(2)\" href=\"javascript:void(0)\" class=\"icon icon-arrow-r next pa\"></a>";
    break;
  case 1:
    str = "<li ></li><li class=\"on\" title=\""
        + data[0].productId+"\""
        + " onclick=\"getInfo("
        + data[0].productId
        + ")\"><a href=\"javascript:void(0)\"> <p class=\"font-16\">"
        + data[0].termDay
        + "天</p><p class=\"font-10\">预计年化收益率</p><p class=\"font-40\"><span class=\"font-30\">"
        + format_yearRate(data[0].yearRate)
        + "%</span></p><p class=\"font-10\">查看详情&gt;</p></a></li><li></li>";
    break;
  default:
    break;
  }
  return str;
}*/