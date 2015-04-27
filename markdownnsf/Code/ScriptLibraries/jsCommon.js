/* ===================================================== */
/* COMMON FUNCTIONS  */
/* ===================================================== */

function fnClickViewRow(thisEvent, refreshID) {
	fnHighLight(thisEvent);
	x$(refreshID).html("<i class='icon-spinner icon-spin icon-large'></i> Loading");
}

//Trap the enter key.
function trapEnterKey(event, id) {
	e = dojo.fixEvent(event);
	if (e.keyCode == dojo.keys.ENTER) {
		x$(id).click();
	}
}

function x$(idTag, param) {
	idTag = idTag.replace(/:/gi, "\\:") + (param ? param : "");
	return ($("#" + idTag));
}

function fnHighLight(event) {
	try {
		var link = (event.target) ? event.target : event.srcElement;
		$row = $(link).closest("tr");
		$("tr.selected").removeClass("selected");
		$row.addClass("selected");
	} catch (e) {
		alert(e);
	}
}

function fnDiscussionReply(event) {
	//debugger;
	var link = (event.target) ? event.target : event.srcElement;
	var post = $(link).closest("li");
	var newPost = $("#newPost");
	//post.addClass("highlight");
	post.append(newPost);
}


function getOuterElement(elStart,sTagName) {
	var el=elStart;
	iSanity=200; // up to 200 deep (safeguard in case of fail)
	while (el.tagName.toLowerCase()!= sTagName.toLowerCase() && iSanity>0)
		{
			el=el.parentNode;
			iSanity--;
		}
	return el
}


//===============discussionReply stuff===============
function fnReply(event) {
	var link = (event.target) ? event.target : event.srcElement;
	var post = $(link).closest("li");
	threadDocClient.create(event,post,"response");
}
function fnMainDoc(event) {
	var link = (event.target) ? event.target : event.srcElement;
	var post = $("#divNewPostTarget");
	threadDocClient.create(event,post,"main");
}


var threadDocClient = 
{

create: function (event,appendTo,sDocType) {
		var link = (event.target) ? event.target : event.srcElement;
		var newPost = XSP.getElementById("newPost"); // the new post 'panel' (div, actually)
		appendTo.append(newPost);
		newPost.style.display="block";
		
		// blank all the fields - otherwise it retains the last comment you entered}
		var arrInputs = newPost.getElementsByTagName("textarea");
		for (i=0;i<arrInputs.length;i++) {	arrInputs[i].value="";	}
		var arrInputs = newPost.getElementsByTagName("input");
		for (i=0;i<arrInputs.length;i++) {	arrInputs[i].value=""; }	
		
		var elSubject = arrInputs[0]; // first input field = Subject
		
		if (sDocType=="response") // hide subject for reply
		{ $('#divMainOnly').hide() } else {  $('#divMainOnly').show() }
	
		},

cancel: function() {
		var newPost = XSP.getElementById("newPost");
	}
}