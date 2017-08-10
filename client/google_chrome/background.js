// The onClicked callback function.
function onClickHandler(info, tab) {
	chrome.tabs.getSelected(null, function(tab) {
			chrome.tabs.sendRequest(
				//Selected tab id
				tab.id,
				//Params inside a object data
				{callFunction: "createSidebar", selectedText: info.selectionText}, 
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
				  
var id = chrome.contextMenus.create({"title": "Check facts", "contexts": contexts,
										 "id": "factchecker", "onclick": onClickHandler});