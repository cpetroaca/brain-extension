// The onClicked callback function.
function onClickHandler(info, tab) {
    console.log("item " + info.menuItemId + " was clicked");
    console.log("info: " + JSON.stringify(info));
    console.log("tab: " + JSON.stringify(tab));
	
	chrome.tabs.getSelected(null, function(tab) {
			chrome.tabs.sendRequest(
				//Selected tab id
				tab.id,
				//Params inside a object data
				{callFunction: "toggleSidebar"}, 
				//Optional callback function
				function(response) {
					console.log(response);
				}
			);
		});
};

// Create one test item for each context type.
var contexts = ["page","selection","link","editable","image","video",
                  "audio"];
				  
var id = chrome.contextMenus.create({"title": "Fact Checker", "contexts": contexts,
										 "id": "factchecker", "onclick": onClickHandler});