rfidreader
		.onResult(function(resultdata) {
			document.getElementById("info").innerHTML = "操作号:"
					+ resultdata.FunctionID;
			switch (resultdata.FunctionID) {
			case 14:
				document.getElementById("CloudReaderVer").value = resultdata.strData;
				break;
			case 5:
				if (resultdata.Result > 0) {
					document.getElementById("cardnum1").value = resultdata.strData
							.substring(0, 10);
				} else {
					document.getElementById("info").innerHTML = GetErrStr(resultdata.Result);
				}
				break;
			case 6:
				if (resultdata.Result > 0) {
					document.getElementById("info").innerHTML = "数据修改成功";
				} else {
					document.getElementById("info").innerHTML = "数据修改失败，错误："
							+ GetErrStr(resultdata.Result);
					// alert("数据修改失败，错误：" + GetErrStr(resultdata.Result));
				}
				break;
			}
		});
var flag = false;
// 定义定时器的id
var id;
// 每10毫秒该值增加1
var seed = 0;
var auto1 = document.getElementById("auto1");
var stop1 = document.getElementById("stop1");
function initsys() {
	auto1.disabled = false;
	stop1.disabled = true;
}
initsys();
function AutoInit() {
	if (!flag) {
		document.getElementById("info").innerHTML = "开始自动刷卡";
		id = window.setInterval(CardInit, 5000);
		flag = true;
		stop1.disabled = false;
		auto1.disabled = true;
	} else {
		alert("已经点击开始，请勿再次点击！");
	}
}
function StopAutoInit() {
	if (flag) {
		document.getElementById("info").innerHTML = "停止自动刷卡";
		window.clearInterval(id);
		flag = false;
		auto1.disabled = false;
		stop1.disabled = true;
	} else {
		alert("还没点击开始，请先点击开始！");
	}
}
function sleep(n) {
	var start = new Date().getTime();
	while (true)
		if (new Date().getTime() - start > n)
			break;
}
function CardInit() {
	// seed++;
	// alert("aaa:" + seed);
	WriteBlockNum();
	sleep(1000);
	// ReadBlockNum();
	// sleep(500);
	WriteBlock();
	sleep(1000);
	// ReadBlockNum1();
	// sleep(500);
}
function ReadBlockNum() {
	var keya1value = "FFFFFFFFFFFF";// keyA
	var BlockID = 1;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = keya1value;
	rfidreader.Repeat = 0
	rfidreader.M1ReadBlock(BlockID, 1);
}
function ReadBlockNum1() {
	var keya1value = "F8E9D3C4B2A5";// keyA
	var BlockID = 1;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = keya1value;
	rfidreader.Repeat = 0
	rfidreader.M1ReadBlock(BlockID, 1);
}

function WriteBlock() {
	var keya1value = "B532343339F7";// keyA
	var keya2value = "F8E9D3C4B2A5";// keyB
	var BlockID = 3;// 块号
	var Key = "FFFFFFFFFFFF";// 原始密码
	var Data = keya1value + "B4BA5469" + keya2value;
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = Key;
	rfidreader.Repeat = 0
	rfidreader.M1WriteBlock(BlockID, Data, 0);
}
function WriteBlockNum() {
	var keya1value = "FFFFFFFFFFFF";// keya
	var BlockID = 1;
	var cardNum = document.getElementById("cardnum");
	var Data = cardNum.value + "10";
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = keya1value;
	rfidreader.Repeat = 0
	rfidreader.M1WriteBlock(BlockID, Data, 1);
	var tmp = Number(cardNum.value) + 1;
	document.getElementById("cardnum").value = tmp;
}
function WriteEmptyBlock() {
	var keya1value = "F8E9D3C4B2A5";
	var BlockID = 3;// 块号
	var Key = keya1value;// 原始密码
	var Data = "FFFFFFFFFFFF" + "B4BA5469" + "FFFFFFFFFFFF";
	rfidreader.KeyMode = 1;
	rfidreader.KeyStringMode = 0;
	rfidreader.KeyString = Key;
	rfidreader.Repeat = 0
	rfidreader.M1WriteBlock(BlockID, Data, 0);
}