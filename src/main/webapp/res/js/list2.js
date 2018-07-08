rfidreader.onResult(function(resultdata) {
	switch (resultdata.FunctionID) {
	case 14:
		document.getElementById("CloudReaderVer").value = resultdata.strData;
		break;
	case 5:
		if (resultdata.Result > 0) {
			var tempStr = resultdata.strData;
			tempStr = tempStr.substring(0, 6);
			var dresult = parseInt(tempStr, 16);
			var dFloat = parseFloat(dresult) / 100;
			$("#cardnum1").val(+dFloat);
		} else {
			alert(GetErrStr(resultdata.Result));
		}
		break;
	case 6:
		if (resultdata.Result > 0) {
			alert("数据修改成功");
		} else {
			alert("数据修改失败，错误：" + GetErrStr(resultdata.Result));
		}
		break;
	}
});
function ReadBlockNum() {
	var rowsSel = datagrid.datagrid("getSelections");
	if (rowsSel.length != 1) {
		showWarnMsg("请选择要使用的秘钥！");
		return;
	}
	var keya1value = rowsSel[0].keyb2value;
	var BlockID = 8;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = keya1value;
	rfidreader.Repeat = 0
	rfidreader.M1ReadBlock(BlockID, 0);
}

function WriteBlock() {
	var rowsSel = datagrid.datagrid("getSelections");
	if (rowsSel.length != 1) {
		showWarnMsg("请选择要使用的秘钥！");
		return;
	}
	var keya1value = rowsSel[0].keyb1value;
	var keya2value = rowsSel[0].keyb2value;
	var BlockID = 11;// 块号
	var Key = "FFFFFFFFFFFF";// 原始密码
	// var Data = keya1value + "FF078060" + keya2value;
	var Data = keya1value + "7F078869" + keya2value;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = Key;
	rfidreader.Repeat = 0
	rfidreader.M1WriteBlock(BlockID, Data, 0);
}
function WriteBlockNum() {
	var rowsSel = datagrid.datagrid("getSelections");
	if (rowsSel.length != 1) {
		showWarnMsg("请选择要使用的秘钥！");
		return;
	}
	var keya1value = rowsSel[0].keyb2value;// keyA
	var BlockID = 8;
	var cardNum = document.getElementById("cardnum").value;
	var total = parseFloat(cardNum) * 100;
	var tempCount = total.toString();
	var strTmp = "0";
	var intCount = parseInt(tempCount);
	var tempStr = intCount.toString(16);
	var tmpCount = 6 - tempStr.length;
	if (tmpCount > 0) {
		for (var i = 1; i < tmpCount; i++) {
			strTmp += "0";
		}
		tempStr = strTmp + tempStr;
	}
	tempStr = tempStr + "00000000000000000000000000";
	if (!isNaN(total) && total >= 1 && total <= 99999) {
		rfidreader.KeyMode = 1;
		rfidreader.KeyStringMode = 0;
		rfidreader.KeyString = keya1value;
		rfidreader.Repeat = 0
		rfidreader.M1WriteBlock(BlockID, tempStr, 0);
	} else {
		alert("充值金额数值填写错误，不能大于999.99");
	}
}
function WriteEmptyBlock() {
	var rowsSel = datagrid.datagrid("getSelections");
	if (rowsSel.length != 1) {
		showWarnMsg("请选择要使用的秘钥！");
		return;
	}
	var keya1value = rowsSel[0].keyb2value;// keyA
	var BlockID = 11;// 块号
	var Key = keya1value;// 原始密码
	var Data = "FFFFFFFFFFFF" + "FF078069" + "FFFFFFFFFFFF";
	alert(Data);
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = Key;
	rfidreader.Repeat = 0
	rfidreader.M1WriteBlock(BlockID, Data, 0);
}