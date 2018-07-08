rfidreader.onResult(function(resultdata) {
	switch (resultdata.FunctionID) {
	case 14:
		document.getElementById("CloudReaderVer").value = resultdata.strData;
		break;
	case 5:
		if (resultdata.Result > 0) {
			document.getElementById("cardnum1").value = resultdata.strData;
		} else {
			alert(GetErrStr(resultdata.Result));
			// document.getElementById("cardnum1").value =
			// GetErrStr(resultdata.Result);
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
	var BlockID = 4;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = keya1value;
	rfidreader.Repeat = 0
	rfidreader.M1ReadBlock(BlockID, 1);
}

function WriteBlock() {
	var rowsSel = datagrid.datagrid("getSelections");
	if (rowsSel.length != 1) {
		showWarnMsg("请选择要使用的秘钥！");
		return;
	}
	var keya1value = rowsSel[0].keyb1value;
	var keya2value = rowsSel[0].keyb2value;
	var BlockID = 7;// 块号
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
	var BlockID = 4;
	var cardNum = document.getElementById("cardnum");
	var Data = cardNum.value;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = keya1value;
	rfidreader.Repeat = 0
	rfidreader.M1WriteBlock(BlockID, Data, 1);
}
function WriteEmptyBlock() {
	var rowsSel = datagrid.datagrid("getSelections");
	if (rowsSel.length != 1) {
		showWarnMsg("请选择要使用的秘钥！");
		return;
	}
	var keya1value = rowsSel[0].keyb2value;// keyA
	var BlockID = 7;// 块号
	var Key = keya1value;// 原始密码
	var Data = "FFFFFFFFFFFF" + "FF078069" + "FFFFFFFFFFFF";
	alert(Data);
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = Key;
	rfidreader.Repeat = 0
	rfidreader.M1WriteBlock(BlockID, Data, 0);
}