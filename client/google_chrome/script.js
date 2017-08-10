function handleRequest(
	request, 
	sender, sendResponse
	) {
	if (request.callFunction == "createSidebar")
		createSidebar(request.selectedText);
}
chrome.extension.onRequest.addListener(handleRequest);

var sidebarOpen = false;
var serverURL = "http://localhost:8082";

function closeSidebar() {
	if(sidebarOpen) {
		var el = document.getElementById('mySidebar');
		el.parentNode.removeChild(el);
		sidebarOpen = false;
	}
}

function createSidebar(selectedText) {
	closeSidebar();
	var sidebar = document.createElement('div');
	sidebar.id = "mySidebar";
	sidebar.style.cssText = "\
		position:fixed;\
		top:0px;\
		right:0px;\
		width:20%;\
		height:50%;\
		background:	#F7F7F7;\
		box-shadow:inset 0 0 5px black;\
		z-index:999999;\
		border-radius: 5px;\
		font-family: Calibri;\
		font-size: 5px;\
	";
	
	var table = document.createElement('table');
	table.style.width = '100%';
	table.style.border = '0px';
	
	var closeTr = table.insertRow();
	var closeTd = closeTr.insertCell();
	var closeLink = document.createElement('a');
	closeLink.addEventListener('click', function() {
        closeSidebar();
    });
	closeLink.innerHTML = "Close[x]";
	closeTd.appendChild(closeLink);
	closeTd.style.cssText = "\
		text-align: right\
	";
	
	var factsTr = table.insertRow();
	var factsTd = factsTr.insertCell();
	factsTd.appendChild(document.createTextNode("Loading..."));
	factsTd.style.cssText = "\
		text-align: center\
	";
	
	sidebar.appendChild(table);
	
	document.body.appendChild(sidebar);
	sidebarOpen = true;
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", serverURL + "/apps/factChecker/validateStatements?text=" + selectedText, false);
	xhr.send();
	
	var jsonData = JSON.parse(xhr.responseText);
	var factsHtml = "";
	for (var i = 0; i < jsonData.length; i++) {
		var relation = jsonData[i];
		var pos = relation.type.lastIndexOf("/") + 1;
		var relationType = relation.type.slice(pos);
		var entitiesString = "";
		for (var j = 0; j < jsonData[i].entities.length; j++) {
			var entity = jsonData[i].entities[j];
			entitiesString += entity.slice(entity.lastIndexOf("/") + 1);
			if (j != jsonData[i].entities.length - 1) {
				entitiesString += ", ";
			}
		}
		
		factsHtml += entitiesString;
		factsHtml += "::";
		factsHtml += relationType;
		if (relation.isFact == true) {
			factsHtml += " <font color=#32CD32>true</font>";
		}
		else {
			factsHtml += " <font color=red>false</font>";
		}
		factsHtml += "<br>";
	}

	if (factsHtml == "") {
		factsHtml = "No relations found";
	}
	
	factsTd.innerHTML = factsHtml;
}