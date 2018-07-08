rfidreader.onResult(function(resultdata) {
	switch (resultdata.FunctionID) {
	case 14:
		document.getElementById("CloudReaderVer").value = resultdata.strData;
		break;
	case 5:
		if (resultdata.Result > 0) {
			if (resultdata.strData == "devcheng") {
				check = true;
				var rowsSel = datagrid.datagrid("getSelections");
				if (rowsSel.length != 1) {
					showWarnMsg("请选择要查看的数据！");
					return;
				}
				var param = zcurdGetParam();
				var userid = param["parent_id"];
				var id = rowsSel[0].id;
				var chargetype = rowsSel[0].chargetype;
				var money = rowsSel[0].money;
				var time = rowsSel[0].time;
				top.openWindow("付款-详情", getCurrUrl("detailPage") + "?id=" + id
						+ "&chargetype=" + chargetype + "&money=" + money
						+ "&time=" + time + "&userid=" + userid, {
					size : '600x400'
				});
			}
		} else {
			var strInfo = GetErrStr(resultdata.Result)
			if (strInfo.indexOf("寻卡失败") >= 0) {
				alert("请放支付密码卡于读卡器");
			} else {
				alert("请选择正确的支付密码卡，多次失败后将会记录操作信息");
			}
		}
		break;
	case 6:
		break;
	}
});
var check = false;
function CheckCard() {
	var keya1value = "F2F2F2F3F2F1";
	var BlockID = 40;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = keya1value;
	rfidreader.Repeat = 0
	rfidreader.M1ReadBlock(BlockID, 1);
}